package light.days

import controllers.routes.AppRoutes
import light.pages.AssetDir
import light.util.DaysHelper
import play.api.mvc.Call
import play.twirl.api.Html

import java.time.LocalDate

trait DayRecord extends AssetDir:
  def key: String
  def dayTitle: String
  def name: String
  def desc: String
  def dates: Seq[LocalDate]
  def yearOpt: Option[Int]
  def content: Html
  def route: Call = AppRoutes.storiesRecord(key)
  def href: String = route.url
  override def assetDir: String = s"${Days.assetDir}/$key"
  def hasDate: Boolean = dates.nonEmpty
  def isToday: Boolean = DayRecord.isToday(dates)
  def dateKey: Option[String] = DayRecord.dateKey(key, dates)
  def dateStr: String = DayRecord.dateStr(dates)
  def ageStr: String = DayRecord.ageStr(dates, yearOpt)
  def title: String = dayTitle
  def subtitle: String = Days.subtitle
end DayRecord

object DayRecord:
  def dateKey(key: String, dates: Seq[LocalDate]): Option[String] =
    dates.headOption
      .map(date => "%3d".format(date.getDayOfYear) + s"-${date.getYear}-$key")

  def isToday(dates: Seq[LocalDate]): Boolean = dates.contains(LocalDate.now())

  def dateStr(dates: Seq[LocalDate]): String =
    dates.headOption
      .map(date => s"${date.getDayOfMonth} ${DaysHelper.monthDate(date.getMonth)} — ")
      .getOrElse("")

  def ageStr(dates: Seq[LocalDate], yearOpt: Option[Int]): String =
    dates.headOption
      .map(_.getYear)
      .orElse(yearOpt)
      .map(y => DaysHelper.years(LocalDate.now.getYear - y))
      .getOrElse("")
end DayRecord
