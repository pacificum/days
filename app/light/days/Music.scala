package light.days

import java.time.{Month, LocalDate}

import light.pages.Page

object Music:
  val music: Seq[MusicRec] = Seq()

  val drafts: Seq[MusicRec] = Seq(
    MusicRec(
      key = "where_have_all_the_flowers_gone",
      name = "Where Have All the Flowers Gone?",
      desc = "Где цветы, дай мне ответ",
      artist = "Pete Seeger",
//      artist = "Пит Сигер",
      date = LocalDate.of(1955, Month.MARCH, 14),
      _ => Page.empty
      //https://ru.wikipedia.org/wiki/Where_Have_All_the_Flowers_Gone
      // A modern folk-style song, inspired lyrically
      // by the traditional Cossack folk song "Koloda-Duda",
      // Pete Seeger borrowed an Irish melody and the first three verses in 1955.
      // Additional verses were added in May 1960 by Joe Hickerson,
      // who turned it into a circular song. Its rhetorical "where?"
      // and meditation on death place the song in the ubi sunt tradition.
      // https://en.wikipedia.org/wiki/Where_Have_All_the_Flowers_Gone%3F
    ),
    MusicRec(
      key = "give_peace_a_chance",
      name = "Give Peace a Chance",
      desc = "Дайте миру шанс",
      artist = "John Lennon",
//      artist = "Джон Леннон",
      date = LocalDate.of(1969, Month.JUNE, 1),
      _ => Page.empty
      //https://ru.wikipedia.org/wiki/Give_Peace_a_Chance
    ),
    MusicRec(
      key = "imagine",
      name = "Imagine",
      artist = "John Lennon",
//      artist = "Джон Леннон",
      date = LocalDate.of(1971, Month.JULY, 5),
      _ => Page.empty
      // https://teletype.in/@peace_calendar/imagine
    ),
    MusicRec(
      key = "all_you_need_is_love",
      name = "All You Need Is Love",
      artist = "The Beatles",
//      artist = "Битлз",
      date = LocalDate.of(1967, Month.JULY, 25),
      _ => Page.empty
      // https://teletype.in/@peace_calendar/allyouneedislove
    ),
    MusicRec(
      key = "tikki",
      name = "Мир во время войны",
      artist = "Тикки Шельен и Игорь Лисов",
      date = LocalDate.of(2014, Month.AUGUST, 4),
      _ => Page.empty
    ),
    MusicRec(
      key = "the_post_war_dream",
      name = "The Post War Dream",
      desc = "Послевоенная мечта",
      artist = "Pink Floyd",
      date = LocalDate.of(1983, Month.MARCH, 21),
      _ => Page.empty
      //Песня британской прогрессивной рок-группы Pink Floyd с альбома The Final Cut (1983)
      //https://ru.wikipedia.org/wiki/The_Post_War_Dream
    ),
    MusicRec(
      key = "the_unknown_soldier",
      name = "The Unknown Soldier",
      artist = "The Doors",
      date = LocalDate.of(1967, Month.NOVEMBER, 25),
      _ => Page.empty
      // Песня американской группы The Doors, первый сингл с их альбома 1968 года Waiting for the Sun.
      // Появление песни было связано с реакцией Джима Моррисона на войну во Вьетнаме а также на то,
      // как конфликт был изображён в американских СМИ в то время
      // https://ru.wikipedia.org/wiki/The_Unknown_Soldier
      //"The Unknown Soldier" has been perceived as Jim Morrison's reaction to the Vietnam War
      // and the way that conflict was portrayed in American media at the time.
      // According to author Richie Weidman, Morrison was inspired to write the lyrics after visiting
      // the Tomb of the Unknown Soldier, at the Arlington National Cemetery,
      // on November 25, 1967; the same day in which the band performed at the
      // Hilton Hotel, International Ballroom.
      //https://en.wikipedia.org/wiki/The_Unknown_Soldier_(song)
    )
  )
end Music
