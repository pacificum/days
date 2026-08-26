package light.days

import play.twirl.api.Html
import play.api.mvc.Call

import java.time.LocalDate
import light.util.DaysHelper

case class Person(
  key: String,
  originalName: String,
  russianName: String,
  info: String,
  dates: Seq[LocalDate],
  yearOpt: Option[Int],
  html: Person => Html
) extends DayRecord:
  override def content: Html = html(this)
  override def name: String = if (russianName.nonEmpty) russianName else originalName
  override def desc: String = if (originalName.nonEmpty) originalName else info
  override def dayTitle: String = dateStr + name
end Person

object Person:
  def apply(
    key: String,
    name: String,
    birth: LocalDate,
    html: Person => Html,
  ): Person = apply(
    key = key,
    russianName = name,
    originalName = "",
    info = "",
    dates = Seq(birth),
    yearOpt = Some(birth.getYear),
    html
  )
  def apply(
    key: String,
    name: String,
    birthYear: Int,
    html: Person => Html,
  ): Person = apply(
    key = key,
    russianName = name,
    originalName = "",
    info = "",
    dates = Seq(),
    yearOpt = Some(birthYear),
    html
  )
  def apply(
    key: String,
    name: String,
    info: String,
    birth: LocalDate,
    html: Person => Html,
  ): Person = apply(
    key = key,
    russianName = name,
    originalName = "",
    info = info,
    dates = Seq(birth),
    yearOpt = Some(birth.getYear),
    html
  )
end Person
