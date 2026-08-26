package exportsite

import play.api.Logger
import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.stream.Materializer
import play.api.libs.ws.ahc.AhcWSClient
import scala.util.{Try, Success, Failure}
import scala.concurrent.Await
import scala.concurrent.duration.*
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Command-line utility for exporting the website
 * Usage examples:
 * - Export to default directory: sbt "runMain exportsite.ExportApp"
 * - Export to custom directory: sbt "runMain exportsite.ExportApp /path/to/export"
 * - Export with custom base URL: sbt "runMain exportsite.ExportApp /path/to/export https://pacificum.org"
 * - Export with local mode enabled: sbt "runMain exportsite.ExportApp /path/to/export https://pacificum.org true"
 */
object ExportApp:

  private val log: Logger = Logger("ExportApp")

  def main(args: Array[String]): Unit =
    val outputDir = args.headOption.getOrElse("exported-site")
    val baseUrl = args.lift(1).getOrElse("http://localhost:9000")

    println(s"Starting website export...")
    println(s"Output directory: $outputDir")
    println(s"Base URL: $baseUrl")
    println()

    Try {
      // Create export configuration
      val config = ExportConfig(
        baseUrl = baseUrl,
        outputDir = outputDir
      )

      // Create WSClient for HTTP requests
      implicit val system: ActorSystem = ActorSystem()
      implicit val materializer: Materializer = Materializer(system)

      val wsClient = AhcWSClient()(materializer)

      try
        // Create export service and perform export
        val exportService = new ExportService(wsClient)

        println("Performing export...")

        // Since this is a CLI app, we need to wait for the Future to complete
        val result = Await.result(exportService.exportWebsite(config), 30.seconds)

        println(result.summary)
        if result.errors.nonEmpty then
          println("\nErrors encountered:")
          result.errors.foreach(error => println(s"  - $error"))

        if result.success then
          println(s"\nExport completed successfully!")
          println(s"Files saved to: $outputDir")
        else
          println(s"\nExport completed with errors.")
          System.exit(1)
      finally
        wsClient.close()
        Await.ready(system.terminate(), 10.seconds)

    } match
      case Success(_) =>
        println("Export process finished.")
      case Failure(exception) =>
        log.error("Export failed", exception)
        println(s"Error: ${exception.getMessage}")
        exception.printStackTrace()
        System.exit(1)

end ExportApp
