package light.days

import play.api.mvc.Call
import play.twirl.api.Html
import java.time.LocalDate
import light.util.DaysHelper

case class Story(
  key: String,
  name: String,
  desc: String,
  dates: Seq[LocalDate],
  yearOpt: Option[Int],
  html: Story => Html
) extends DayRecord:
  override def content: Html = html(this)
  override def dayTitle: String =
    dates
      .distinctBy(_.getDayOfYear)
      .map(date => s"${date.getDayOfMonth} ${DaysHelper.monthDate(date.getMonth)}")
      .mkString(" и ") + (if hasDate then " — " else "") + name

end Story

object Story:

  def apply(
    key: String,
    title: String,
    html: Story => Html
  ): Story = new Story(
    key = key,
    name = title,
    desc = "",
    dates = Seq.empty,
    yearOpt = None,
    html
  )

  def apply(
    key: String,
    title: String,
    date: LocalDate,
    html: Story => Html
  ): Story = new Story(
    key = key,
    name = title,
    desc = "",
    dates = Seq(date),
    yearOpt = Some(date.getYear),
    html
  )

  def apply(
    key: String,
    title: String,
    desc: String,
    date: LocalDate,
    html: Story => Html
  ): Story = new Story(
    key = key,
    name = title,
    desc = desc,
    dates = Seq(date),
    yearOpt = Some(date.getYear),
    html
  )

  def apply(
    key: String,
    title: String,
    desc: String,
    year: Int,
    html: Story => Html
  ): Story = new Story(
    key = key,
    name = title,
    desc = desc,
    dates = Seq.empty,
    yearOpt = Some(year),
    html
  )

  def apply(
    key: String,
    title: String,
    year: Int,
    html: Story => Html
  ): Story = new Story(
    key = key,
    name = title,
    desc = "",
    dates = Seq.empty,
    yearOpt = Some(year),
    html
  )

  def apply(
    key: String,
    title: String,
    desc: String,
    dates: Seq[LocalDate],
    html: Story => Html
  ): Story = new Story(
    key = key,
    name = title,
    desc = desc,
    dates = dates,
    yearOpt = dates.headOption.map(_.getYear),
    html
  )

  def apply(
    key: String,
    title: String,
    dates: Seq[LocalDate],
    html: Story => Html
  ): Story = new Story(
    key = key,
    name = title,
    desc = "",
    dates = dates,
    yearOpt = dates.headOption.map(_.getYear),
    html
  )
end Story
