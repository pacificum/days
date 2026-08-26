package light.util

import play.api.i18n.Messages
import play.api.mvc.*

import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.{Month, LocalDate}

object DaysHelper:

  val dateFormatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("dd.MM.YYYY")

  val meDay: LocalDate = LocalDate.of(1980, Month.JULY, 12)
  val startDay: LocalDate = LocalDate.of(2015, Month.JANUARY, 1)

  def weeks(date: LocalDate = LocalDate.now): Int = weeks(countDays(startDay, date))

  def weeks(days: Int): Int = (days + 6) / 7

  def countDays(from: LocalDate = startDay, to: LocalDate = LocalDate.now) =
    ChronoUnit.DAYS.between(from, to).toInt + 1

  def weekMsg(n: Int = weeks())(using messages: Messages) =
    numeral(n, feminine).getOrElse(n.toString)

  def dayMsg(n: Int = countDays())(using messages: Messages) =
    numeral(n, masculine).getOrElse(n.toString)

  def numeral(n: Int, gender: String = "")(using messages: Messages): Option[String] =
    n match
      case n if n < 0 => None
      case n if n <= 20 || n < 2000 && n % 100 == 0 || n < 100 && n % 10 == 0 => msg(n, gender)
      case n if n < 100 =>
        for
          tens <- msg((n / 10) * 10)
          units <- msg(n % 10, gender)
        yield s"$tens $units"
      case n if n < 1000 =>
        for
          hundreds <- msg((n / 100) * 100)
          rest <- numeral(n % 100, gender)
        yield s"$hundreds $rest"
      case n if n < 2000 =>
        for
          hundreds <- msg((n % 1000 / 100) * 100)
          rest <- numeral(n % 100, gender)
        yield s"тысяча $hundreds $rest"
      case n if n < 1000000 =>
        for
          thousands <- msg((n / 1000) * 1000)
          rest <- numeral(n % 1000, gender)
        yield s"$thousands $rest"
      case _ => None
  end numeral

  def masculine = "m."

  def feminine = "f."

  private def msg(keyPart: Int, gender: String = "")(using messages: Messages) =
    val key = s"nums.$gender$keyPart"
    if (Messages.isDefinedAt(key)) Option(Messages(key)) else None

  def monthDate(m: Month): String =
    Map(
      Month.JANUARY -> "января",
      Month.FEBRUARY -> "февраля",
      Month.MARCH -> "марта",
      Month.APRIL -> "апреля",
      Month.MAY -> "мая",
      Month.JUNE -> "июня",
      Month.JULY -> "июля",
      Month.AUGUST -> "августа",
      Month.SEPTEMBER -> "сентября",
      Month.OCTOBER -> "октября",
      Month.NOVEMBER -> "ноября",
      Month.DECEMBER -> "декабря"
    )(m)

  def years(y: Int): String =
    if (y % 100 <= 20 && y % 100 >= 10) s"$y лет"
    else y % 10 match
      case 1 => s"$y год"
      case 2 | 3 | 4 => s"$y года"
      case _ => s"$y лет"

end DaysHelper
