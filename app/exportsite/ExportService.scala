package exportsite

import javax.inject.{Inject, Singleton}
import light.days.Days
import play.api.Logger
import play.api.libs.ws.WSClient
import java.nio.file.{Files, Path, Paths, StandardOpenOption}
import java.nio.charset.StandardCharsets
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

case class ExportConfig(
  baseUrl: String = "http://localhost:9000",
  outputDir: String = "exported-site",
)

case class ExportContext(
  baseUrl: String,
  exportPath: Path
)

case class ExportTask(
  url: String,
  filePath: Path,
  label: String
)

case class ExportResult(
  pageCount: Int,
  errors: Seq[String] = Seq.empty
):

  def success: Boolean = errors.isEmpty

  def summary: String =
    val status = if success then "completed" else "completed with errors"
    s"Export $status. Pages exported: $pageCount, Errors: ${errors.size}"

  def combine(other: ExportResult) =
    ExportResult(
      pageCount = pageCount + other.pageCount,
      errors = errors ++ other.errors
    )

  def +(other: ExportResult): ExportResult = combine(other)

end ExportResult

@Singleton
class ExportService @Inject()(
  wsClient: WSClient
)(implicit ec: ExecutionContext):

  private val log: Logger = Logger("ExportService")

  def exportWebsite(config: ExportConfig): Future[ExportResult] =
    log.info(s"Starting website export to directory: ${config.outputDir}")
    log.info(s"Using base URL: ${config.baseUrl}")

    val context = ExportContext(
      baseUrl = config.baseUrl,
      exportPath = Paths.get(config.outputDir)
    )

    import context.*

    // Create export directory if it doesn't exist
    if !Files.exists(exportPath) then
      Files.createDirectories(exportPath)

    // Wait for all exports to complete, then copy static assets
    Future.sequence(Seq(
      exportDaysMain(context),
      exportDays(context),
      exportWebjarAssets(context),
      Future.fromTry(exportAssets(context))
    )).map { results =>
      val result: ExportResult = results.reduceLeft(_ + _)
      log.info(result.summary)
      result
    }
  end exportWebsite


  // Export the days listing page (DaysMain)
  private def exportDaysMain(context: ExportContext): Future[ExportResult] =
    import context.*
    exportPages(Seq(Days)) { page =>
      val pageDir = exportPath.resolve(page.key)
      ExportTask(
        s"$baseUrl/${page.key}/",
        pageDir.resolve("index.html"),
        s"page: ${page.key}"
      )
    }
  end exportDaysMain
  
  // Export day records
  private def exportDays(context: ExportContext): Future[ExportResult] =
    import context.*
    val daysDir = exportPath.resolve("days")
    exportPages(Days.records) { record =>
      ExportTask(
        s"$baseUrl/days/${record.key}/",
        daysDir.resolve(record.key).resolve("index.html"),
        s"day record: ${record.key}"
      )
    }
  end exportDays

  // Fetch and save a page per item, combining the individual results
  private def exportPages[A](items: Seq[A])(target: A => ExportTask): Future[ExportResult] =
    Future
      .sequence(items.map(target).map(exportPage))
      .map(_.reduceLeft(_ + _))

  // Fetch and save a single page, wrapping the outcome as an ExportResult
  private def exportPage(task: ExportTask): Future[ExportResult] =
    import task.*
    Files.createDirectories(filePath.getParent)
    fetchAndSavePage(task)
      .map { _ =>
        log.info(s"Exported $label")
        ExportResult(1)
      }.recover {
        case e: Exception =>
          val error = s"Failed to export $label: ${e.getMessage}"
          log.error(error, e)
          ExportResult(pageCount = 0, errors = Seq(error))
      }

  // Webjar-served assets (font-awesome CSS + its webfonts, jquery) referenced from
  // app/views/lib/main.scala.html via routes.Assets.at(...) — these live in the
  // webjars-play pipeline output, not app/public, so they must be fetched from the
  // running server rather than copied from disk like copyStaticAssets does
  private val webjarAssetPaths: Seq[String] = Seq(
    "lib/font-awesome/css/all.min.css",
    "lib/jquery/jquery.min.js",
    "lib/font-awesome/webfonts/fa-brands-400.ttf",
    "lib/font-awesome/webfonts/fa-brands-400.woff2",
    "lib/font-awesome/webfonts/fa-regular-400.ttf",
    "lib/font-awesome/webfonts/fa-regular-400.woff2",
    "lib/font-awesome/webfonts/fa-solid-900.ttf",
    "lib/font-awesome/webfonts/fa-solid-900.woff2",
    "lib/font-awesome/webfonts/fa-v4compatibility.ttf",
    "lib/font-awesome/webfonts/fa-v4compatibility.woff2"
  )

  private def exportWebjarAssets(context: ExportContext): Future[ExportResult] =
    import context.*
    val assetsDir = exportPath.resolve("assets")
    Future
      .sequence(webjarAssetPaths.map { assetPath =>
        downloadAsset(s"$baseUrl/assets/$assetPath", assetsDir.resolve(assetPath), s"webjar asset: $assetPath")
      })
      .map(_.reduceLeft(_ + _))
  end exportWebjarAssets

  // Fetch and save a binary asset as-is (no UTF-8 text decoding, unlike fetchAndSavePage)
  private def downloadAsset(url: String, filePath: Path, label: String): Future[ExportResult] =
    log.info(s"Fetching asset: $url")
    Files.createDirectories(filePath.getParent)
    wsClient.url(url).get().map { response =>
      if response.status == 200 then
        Files.write(filePath, response.bodyAsBytes.toArray, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
        log.info(s"Saved asset to: $filePath")
        ExportResult(1)
      else
        throw new RuntimeException(s"HTTP ${response.status} for $url")
    }.recover {
      case e: Exception =>
        val error = s"Failed to export $label: ${e.getMessage}"
        log.error(error, e)
        ExportResult(pageCount = 0, errors = Seq(error))
    }
  end downloadAsset

  private def exportAssets(context: ExportContext): Try[ExportResult] =
    import context.*
    Try {
      copyStaticAssets(exportPath)
      log.info("Copied static assets")
      ExportResult(0)
    }.recover {
      case e: Exception =>
        val error = s"Failed to copy static assets: ${e.getMessage}"
        log.error(error, e)
        ExportResult(pageCount = 0, errors = Seq(error))
    }
  end exportAssets

  private def fetchAndSavePage(task: ExportTask): Future[Unit] =
    log.info(s"Fetching page: ${task.url}")
    wsClient.url(task.url).get().map { response =>
      if response.status == 200 then
        val content = response.body
        Files.write(task.filePath, content.getBytes(StandardCharsets.UTF_8),
          StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
        log.info(s"Saved page to: ${task.filePath}")
      else
        throw new RuntimeException(s"HTTP ${response.status} for ${task.url}")
    }
  end fetchAndSavePage

  private def copyStaticAssets(exportPath: Path): Unit =
    val publicDir = Paths.get("public")
    if Files.exists(publicDir) then
      val assetsDir = exportPath.resolve("assets")
      Files.createDirectories(assetsDir)

      // Copy CSS files
      copyDirectory(publicDir.resolve("css"), assetsDir.resolve("css"))

      // Copy JS files
      copyDirectory(publicDir.resolve("js"), assetsDir.resolve("js"))

      // Copy Bootstrap
      copyDirectory(publicDir.resolve("bootstrap"), assetsDir.resolve("bootstrap"))

      // Copy web fonts
      copyDirectory(publicDir.resolve("webfonts"), assetsDir.resolve("webfonts"))

      // Copy icons
      copyDirectory(publicDir.resolve("icons"), assetsDir.resolve("icons"))

      // Copy any other static files
      Files.list(publicDir)
        .filter(Files.isRegularFile(_))
        .forEach { file =>
          val fileName = file.getFileName.toString
          if fileName.endsWith(".html") || fileName.endsWith(".txt") || fileName.endsWith(".xml") then
            Files.copy(file, assetsDir.resolve(fileName), java.nio.file.StandardCopyOption.REPLACE_EXISTING)
        }
  end copyStaticAssets

  private def copyDirectory(source: Path, target: Path): Unit =
    if Files.exists(source) then
      Files.createDirectories(target)

      import java.nio.file.attribute.BasicFileAttributes
      import java.nio.file.{FileVisitResult, SimpleFileVisitor}

      val visitor = new SimpleFileVisitor[Path]:
        override def preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult =
          val targetDir = target.resolve(source.relativize(dir))
          Files.createDirectories(targetDir)
          FileVisitResult.CONTINUE

        override def visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult =
          val targetFile = target.resolve(source.relativize(file))
          Files.copy(file, targetFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING)
          FileVisitResult.CONTINUE

      Files.walkFileTree(source, visitor)
  end copyDirectory

end ExportService
