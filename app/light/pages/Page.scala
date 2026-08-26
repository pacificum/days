package light.pages

import play.api.mvc.*
import play.twirl.api.Html

trait KeyInfo:
  def key: String
end KeyInfo

trait RouteInfo extends KeyInfo:
  def route: Call
  def href: String = route.url
end RouteInfo

object Page:
  val empty: Html = Html("")
end Page
