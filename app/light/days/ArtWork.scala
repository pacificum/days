package light.days

import play.api.mvc.Call
import play.twirl.api.Html
import java.time.LocalDate

case class ArtWork(
  key: String,
  name: String,
  desc: String,
  author: String,
  year: Int,
  date: Option[LocalDate],
  html: ArtWork => Html
) extends DayRecord:
  override def content: Html = html(this)
  override def dayTitle: String = dateStr + titleStr
  private def titleStr: String = if (author.nonEmpty) s"«$name», $author" else name
  override def dates: Seq[LocalDate] = date.toSeq
  override def yearOpt: Option[Int] = Some(year)
end ArtWork

object ArtWork:
  def apply(
    key: String,
    name: String,
    author: String,
    year: Int,
    html: ArtWork => Html
  ): ArtWork = new ArtWork(
    key= key,
    name = name,
    desc = "",
    author = author,
    year = year,
    date = None,
    html
  )
  def apply(
    key: String,
    name: String,
    author: String,
    date: LocalDate,
    html: ArtWork => Html
  ): ArtWork = new ArtWork(
    key = key,
    name = name,
    desc = "",
    author = author,
    year = date.getYear,
    date = Some(date),
    html
  )

  def apply(
    key: String,
    name: String,
    desc: String,
    author: String,
    date: LocalDate,
    html: ArtWork => Html
  ): ArtWork = new ArtWork(
    key = key,
    name = name,
    desc = desc,
    author = author,
    year = date.getYear,
    date = Some(date),
    html
  )
end ArtWork
