package light.days

import controllers.routes.AppRoutes
import light.pages.AssetDir
import play.api.mvc.*
import play.twirl.api.Html

import java.time.LocalDate

trait Days:
  val stories: Seq[Story] = Stories.stories
  val people: Seq[Person] = People.people
  val music: Seq[MusicRec] = Music.music
  val movies: Seq[ArtWork] = Movies.movies
  val art: Seq[ArtWork] = GraphicArt.art
  val records: Seq[DayRecord] = stories ++ people ++ music ++ movies ++ art

  def days: Seq[DayRecord] = records.filter(_.hasDate)
end Days

object Days extends Days with AssetDir:
  def key = "days"
  val subtitle: String = "истории о мире и свободе"
  def route: Call = AppRoutes.page(key)
  def href: String = route.url
  def html: Html = views.html.days._days()
  override def assetDir: String = key

  val recordByKey: Map[String, DayRecord] =
    records.foldLeft(Map[String, DayRecord]()) {
      case (map, record) => map.updated(record.key, record)
    }

  val sortedDays: Seq[DayRecord] = records.filter(_.dateKey.nonEmpty).sortBy(_.dateKey.get)

  def currentDays: Seq[DayRecord] =
    val end: Seq[DayRecord] =
      sortedDays
        .filter(_.dates.nonEmpty)
        .takeWhile(_.dates.head.getDayOfYear < LocalDate.now.getDayOfYear)
    end.length - 1 match
      case i if i >= 0 => sortedDays.drop(i) ++ end.take(i)
      case _ => sortedDays.drop(sortedDays.length - 1) ++ sortedDays.take(sortedDays.length - 1)

  def day(key: String): DayRecord = recordByKey(key)

  def lookupContent(key: String)(m: MessagesRequest[AnyContent]): Option[Html] =
    recordByKey.get(key)
      .flatMap(p => Some(views.html.days.parts.daypage(p)))

  def lookupMain(key: String)(m: MessagesRequest[AnyContent]): Option[Html] =
    Option.when(key == this.key)(html)

end Days
