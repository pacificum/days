package controllers

import javax.inject.Inject
import play.api.Logger
import play.api.mvc.*

import light.days.*
import light.pages.*

class AppRoutes @Inject()(mcc: MessagesControllerComponents)
  extends MessagesAbstractController(mcc) {

  private val log: Logger = Logger("AppRoutes")
  
  def page(key: String): Action[AnyContent] = Action { request =>
    Days.
      lookupMain(key)(request)
      .map(Ok(_))
      .getOrElse(NotFound)
  }

  def storiesRecord(key: String): Action[AnyContent] = Action { request =>
    Days
      .lookupContent(key)(request)
      .map(Ok(_))
      .getOrElse(NotFound)
  }

  def storiesRedirect(item: String): Action[AnyContent] = Action {
    Redirect(routes.AppRoutes.storiesRecord(item))
  }

  def asset(location: String): Action[AnyContent] = Action {
    val fullPath = Files.assetAt(location)
    log.info(s"Looking for an asset with full path: $fullPath")
    Redirect(fullPath)
  }

  def font(location: String): Action[AnyContent] = asset(s"assets/fonts/$location")

  def me: Action[AnyContent] = Action(Ok(views.html.days.me()))

}
