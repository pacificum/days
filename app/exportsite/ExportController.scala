package exportsite

import javax.inject.Inject
import play.api.Logger
import play.api.mvc.*
import scala.concurrent.ExecutionContext


/**
 * Controller for exporting the website to static files
 *
 * Usage:
 *    - Default export: GET http://localhost:9000/export
 *    - Custom directory: GET http://localhost:9000/export?outputDir=my-export
 *    - Enable local mode: GET http://localhost:9000/export?enableLocal=true
 *    - All parameters: GET http://localhost:9000/export?outputDir=my-export&enableLocal=true
 *
 * 4. Alternative CLI usage:
 *    sbt "runMain exportsite.ExportApp [outputDir] [baseUrl] [enableLocal]"
 */
class ExportController @Inject()(
  mcc: MessagesControllerComponents,
  exportService: ExportService
)(implicit ec: ExecutionContext) extends MessagesAbstractController(mcc):

  private val log: Logger = Logger("ExportController")

  /**
   * Export the entire website to a specified directory
   * Parameters:
   * - outputDir: Directory where to save the exported files
   * - enableLocal: Whether to enable local development mode
   */
  def exportSite(
    outputDir: Option[String] = None
  ): Action[AnyContent] = Action.async { implicit request =>

    val baseUrl = if request.domain == "localhost" then "http://localhost:9000" else s"https://${request.host}"
    val config = ExportConfig(
      baseUrl = baseUrl,
      outputDir = outputDir.getOrElse("exported-site")
    )

    log.info(s"Starting website export with config: $config")

    exportService.exportWebsite(config).map { result =>
      if result.success then
        Ok(result.summary)
      else
        Ok(s"${result.summary}\n\nErrors:\n${result.errors.mkString("\n")}")
    }.recover {
      case e: Exception =>
        log.error(s"Export failed with exception: ${e.getMessage}", e)
        InternalServerError(s"Export failed: ${e.getMessage}")
    }
  }

end ExportController
