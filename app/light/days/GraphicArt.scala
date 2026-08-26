package light.days

import light.pages.Page

import java.time.{LocalDate, Month}

object GraphicArt:
  val art: Seq[ArtWork] = Seq(
    ArtWork(
      key = "picasso_dove",
      name = "Голубка",
      author = "Пикассо",
      desc = "Голубь мира",
      LocalDate.of(1961, Month.DECEMBER, 28),
      views.html.days.picasso_dove.f
    ),
    ArtWork(
      key = "banksy-love-is-in-the-air",
      name = "Метатель цветов",
      author = "Бэнкси",
      year = 2003,
      views.html.days.banksy_love_is_in_the_air.f
    )
  )

  val drafts: Seq[ArtWork] = Seq(
    ArtWork(
      key = "vereshchagin",
      name = "Апофеоз войны",
      author = "Василий Верещагин",
      year = 1871,
      // Vereshcagin's Birthday LocalDate.of(1842, Month.OCTOBER, 25), // Vereshcagin's Birthday
      _ => Page.empty
      //https://www.facebook.com/peaceandloveplatform/posts/pfbid0JfuFCEq4PrGqfQbTcdYNaQeCMfkT8mb7opsradhcs918yqYfZdJL6poj38RKwLNUl
    )
  )
end GraphicArt
