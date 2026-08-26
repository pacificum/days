package light.days

import play.api.mvc.Call
import play.twirl.api.Html
import java.time.LocalDate

case class MusicRec(
  key: String,
  name: String,
  artist: String,
  desc: String,
  year: Int,
  date: Option[LocalDate],
  html: MusicRec => Html
) extends DayRecord:
  override def dayTitle: String = dateStr + titleStr
  override def content: Html = html(this)
  private def titleStr: String = if (artist.nonEmpty) s"$artist — $name" else name
  override def href: String = route.url
  override def dates: Seq[LocalDate] = date.toSeq
  override def yearOpt: Option[Int] = Some(year)
end MusicRec

object MusicRec:

  def apply(
    key: String,
    name: String,
    artist: String,
    desc: String,
    date: LocalDate,
    html: MusicRec => Html
  ): MusicRec = new MusicRec(
    key = key,
    name = name,
    artist = artist,
    desc = desc,
    year = date.getYear,
    date = Some(date),
    html = html
  )

  def apply(
    key: String,
    name: String,
    artist: String,
    date: LocalDate,
    html: MusicRec => Html
  ): MusicRec = new MusicRec(
    key = key,
    name = name,
    artist = artist,
    desc = "",
    year = date.getYear,
    date = Some(date),
    html = html
  )

  def apply(
    key: String,
    name: String,
    artist: String,
    year: Int,
    html: MusicRec => Html
  ): MusicRec = new MusicRec(
    key = key,
    name = name,
    desc = "",
    artist = artist,
    year = year,
    date = None,
    html
  )
end MusicRec
