package light.days

import java.time.{Month, LocalDate}

import light.pages.Page

object Stories:

  val stories: Seq[Story] = Seq(
    Story(
      key = "christmas",
      title = "Рождественское перемирие",
      LocalDate.of(1914, Month.DECEMBER, 25),
      views.html.days.christmas.f
    ),
    Story(
      key = "peace_symbol",
      title = "пацифик",
      desc = "символ мира ☮︎",
      dates = Seq(
        LocalDate.of(1958, Month.FEBRUARY, 21),
        LocalDate.of(1958, Month.APRIL, 4)
      ),
      views.html.days.peace_symbol.f
    ),
    Story(
      key = "peace_pole",
      title = "Полюс мира",
      year = 1976,
      views.html.days.peace_pole.f
    ),
    Story(
      key = "hippie",
      title = "«День хиппи»",
      Seq(
        LocalDate.of(1971, Month.JUNE, 1),
        LocalDate.of(1982, Month.JUNE, 1)
      ),
      views.html.days.hippie.f
      //https://www.dropbox.com/s/z9o1efkuf5kxvn6/june1demo.pdf
      //https://docs.google.com/document/d/1eiusDfNue8AMGofM4zXT3Zmuek_EB_pGCAmeTJd5rfA/edit
    )
  )

  val drafts: Seq[Story] = Seq(
    Story(
      key = "eirene",
      title = "Эйрена",
      _ => Page.empty
    ),
    Story(
      key = "hugh_thompson",
      title = "пилот Хью Томпсон останавливает резню в Сонгми",
      LocalDate.of(1968, Month.MARCH, 16),
      _ => Page.empty
      //https://vk.com/wall-84513695_1056
      //https://ru.wikipedia.org/wiki/%D0%A2%D0%BE%D0%BC%D0%BF%D1%81%D0%BE%D0%BD,_%D0%A5%D1%8C%D1%8E
      //https://ru.wikipedia.org/wiki/%D0%9C%D0%B0%D1%81%D1%81%D0%BE%D0%B2%D0%BE%D0%B5_%D1%83%D0%B1%D0%B8%D0%B9%D1%81%D1%82%D0%B2%D0%BE_%D0%B2_%D0%A1%D0%BE%D0%BD%D0%B3%D0%BC%D0%B8
      //Hugh Clowers Thompson Jr.
    ),
    Story(
      key = "nutopia",
      title = "Нутопия",
      LocalDate.of(1973, Month.APRIL, 1),
      _ => Page.empty
      //https://www.facebook.com/peaceandloveplatform/posts/pfbid0UQX1jGSyuTRWkexz6aotkrWYCj6fG2q5TZGgGGWNGKceTgDT9dZK5naZyi59j2fDl
    ),
    Story(
      key = "conscientious",
      title = "День отказчика от военной службы по убеждениям совести",
      LocalDate.of(1961, Month.MAY, 15),
      _ => Page.empty
      //https://teletype.in/@peace_calendar/conscientious
      //https://www.facebook.com/peaceandloveplatform/posts/pfbid0TY4csdm6zsCaJfEHdiTmttfssmDCpp8eUXFfn46QaWYD4uiR8oXKiqvqbr84nET9l
    ),
    Story(
      key = "hakuchi",
      title = "Идиот, Акира Куросава",
      LocalDate.of(1951, Month.MAY, 23),
      _ => Page.empty
      //японская чёрно-белая драма режиссёра Акиры Куросавы
      //https://ru.wikipedia.org/wiki/%D0%98%D0%B4%D0%B8%D0%BE%D1%82_(%D1%84%D0%B8%D0%BB%D1%8C%D0%BC,_1951)
    ),
    Story(
      key = "vancouver_riot_kissing_couple",
      title = "Целующаяся пара во время бунта в Ванкувере",
      LocalDate.of(2011, Month.JUNE, 15),
      _ => Page.empty
      //https://teletype.in/@peace_calendar/vancouver_riot_kissing_couple
    ),
    Story(
      key = "russell_einstein_manifesto",
      title = "Манифест Рассела — Эйнштейна",
      LocalDate.of(1955, Month.JULY, 9),
      _ => Page.empty
      //https://www.facebook.com/peaceandloveplatform/posts/pfbid02Q2pe1FJMsPD8Fk8qEbr6btdDpvgHY2mZx6LTTSxDqV4vTMoUhY5Vtzth9kQtVjhEl
    ),
    Story(
      key = "pugwash",
      title = "Пагуошское движение и манифест Рассела — Эйнштейна",
      LocalDate.of(1955, Month.JULY, 9),
      _ => Page.empty
      // https://teletype.in/@peace_calendar/pugwash
    ),
    Story(
      key = "tolstoi_peace_congress",
      title = "cтатья Льва Толстого для Конгресса мира",
      LocalDate.of(1909, Month.AUGUST, 17),
      _ => Page.empty
      //https://teletype.in/@peace_calendar/tolstoi
    ),
    Story(
      key = "peaceoneday",
      title = "День мира",
      LocalDate.of(2001, Month.SEPTEMBER, 21),
      _ => Page.empty
    ),
    Story(
      key = "march_on_the_pentagon",
      title = "Марш на Пентагон",
      LocalDate.of(1967, Month.OCTOBER, 21),
      _ => Page.empty
      //https://en.wikipedia.org/wiki/March_on_the_Pentagon
      //https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D1%85%D0%BE%D0%B4_%D0%BD%D0%B0_%D0%9F%D0%B5%D0%BD%D1%82%D0%B0%D0%B3%D0%BE%D0%BD
    ),
    Story(
      key = "white_rose",
      title = "студенческая группа сопротивления «Белая роза»",
      LocalDate.of(1942, Month.JUNE, 27),
      _ => Page.empty
    ),
    //«Купол атомного взрыва», Купол Гэмбаку, «Атомный купол» — Мемориал мира в Хиросиме
    //https://ru.wikipedia.org/wiki/%D0%9C%D0%B5%D0%BC%D0%BE%D1%80%D0%B8%D0%B0%D0%BB_%D0%BC%D0%B8%D1%80%D0%B0_%D0%B2_%D0%A5%D0%B8%D1%80%D0%BE%D1%81%D0%B8%D0%BC%D0%B5
  )

end Stories
