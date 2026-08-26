package light.days

import light.pages.Page

import java.time.{Month, LocalDate}

object People:
  val people: Seq[Person] = Seq(
    Person(
      key = "flowers_are_better_than_bullets",
      name = "Эллисон Краузе︎",
      info = "«Цветы лучше пуль»",
      LocalDate.of(1951, Month.APRIL, 23),
      views.html.days.flowers_are_better_than_bullets.f
    )
  )

  val drafts: Seq[Person] = Seq(
    Person(
      key = "kurt_vonnegut",
      name = "Курт Воннегут",
      birth = LocalDate.of(1922, Month.NOVEMBER, 11),
      views.html.days.vonnegut.f
      //https://www.facebook.com/peaceandloveplatform/posts/pfbid0FtNz2VaPLXjbe7iE7VpePZLK4eRfRCj8Ri8tqWpAYWcLKhMRKE2dMh3q9QDc32cwl
    ),
    Person(
      key = "soffie_scholl",
      name = "Софи Шолль",
      info = "«Белая роза»",
      birth = LocalDate.of(1921, Month.MAY, 9),
      _ => Page.empty
      //https://www.facebook.com/peaceandloveplatform/posts/pfbid02FBRc1GejmK1dxPy52KnLhY9j13tP65ZbNCpVcdxCfBwwMqf9ksTjZKrjeH78hGUjl
    ),
    Person(
      key = "martin_luther_king_jr",
      name = "Мартин Лютер Кинг",
      birth = LocalDate.of(1929, Month.JUNE, 15),
      _ => Page.empty
      //https://vk.com/wall-49743685_20675
      //https://ru.wikipedia.org/wiki/%D0%9A%D0%B8%D0%BD%D0%B3,_%D0%9C%D0%B0%D1%80%D1%82%D0%B8%D0%BD_%D0%9B%D1%8E%D1%82%D0%B5%D1%80
    ),
    Person(
      key = "viktor_tsoi",
      name = "Виктор Цой",
      birth = LocalDate.of(1962, Month.JUNE, 21),
      _ => Page.empty
      //https://teletype.in/@peace_calendar/victor
      //https://ru.wikipedia.org/wiki/%D0%A6%D0%BE%D0%B9,_%D0%92%D0%B8%D0%BA%D1%82%D0%BE%D1%80_%D0%A0%D0%BE%D0%B1%D0%B5%D1%80%D1%82%D0%BE%D0%B2%D0%B8%D1%87
    ),
    Person(
      key = "henry_david_thoreau",
      name = "Генри Дэвид Торо",
      birth = LocalDate.of(1817, Month.JULY, 12),
      _ => Page.empty
      //https://www.facebook.com/firebird.watch/posts/pfbid02FtNwzWKKk9FSgS7Po4kWHJaY3zLdscEcWUM1S89adwGNGNGmWJ7QnNZy9AJfGQnTl
    ),
    Person(
      key = "ernest_hemingway",
      name = "Эрнест Хемингуэй",
      birth = LocalDate.of(1899, Month.JULY, 21),
      _ => Page.empty
      // Прощай, оружие! роман Эрнеста Хемингуэя, вышедший в 1929
      //https://ru.wikipedia.org/wiki/%D0%9F%D1%80%D0%BE%D1%89%D0%B0%D0%B9,_%D0%BE%D1%80%D1%83%D0%B6%D0%B8%D0%B5!
    ),
    Person(
      key = "benedictus_xv",
      name = "Папа Бенедикт XV",
      info = "Джакомо делла Кьеза",
      birth = LocalDate.of(1914, Month.SEPTEMBER, 3),
      _ => Page.empty
      //https://novayagazeta.ru/articles/2017/11/09/74495-kak-rimskiy-papa-voynu-ostanavlival
      //https://www.facebook.com/peaceandloveplatform/posts/pfbid0rcyid7AvyDYpehP7gmTZgggR5Y1ubBJ9NsWen7mpDnYPNHrKsgc9jkxrbpSwab61l
    ),
    Person(
      key = "gandhi",
      name = "Мохандас Ганди",
      birth = LocalDate.of(1869, Month.OCTOBER, 2),
      _ => Page.empty
      //https://teletype.in/@peace_calendar/gandhi
    ),
    Person(
      key = "ossietzky",
      name = "Карл фон Осецкий",
      birth = LocalDate.of(1989, Month.OCTOBER, 3),
      _ => Page.empty
      //https://www.facebook.com/peaceandloveplatform/posts/pfbid04eqFecU4XA2MxJoyX9QRUsZZhXjPwuuzGyLQiNDhv9zK8JEX6KqVakAggXLfCea4l
    ),
    Person(
      key = "lennon",
      name = "Джон Леннон",
      birth = LocalDate.of(1940, Month.OCTOBER, 9),
      _ => Page.empty
      // https://teletype.in/@peace_calendar/lennon
      // https://ru.wikipedia.org/wiki/%D0%9B%D0%B5%D0%BD%D0%BD%D0%BE%D0%BD,_%D0%94%D0%B6%D0%BE%D0%BD
    ),
    Person(
      key = "nansen",
      name = "Фритьоф Нансен",
      birth = LocalDate.of(1861, Month.OCTOBER, 10),
      _ => Page.empty
      //https://teletype.in/@peace_calendar/nansen
      //https://ru.wikipedia.org/wiki/%D0%9D%D0%B0%D0%BD%D1%81%D0%B5%D0%BD,_%D0%A4%D1%80%D0%B8%D1%82%D1%8C%D0%BE%D1%84
    ),
    Person(
    key = "peter-jarman",
    name = "Питер Джарман",
    birthYear = 1935,
    _ => Page.empty
//      https://t.me/quakers_ru/502
//      https://quakers.ru/%d0%bf%d0%b8%d1%82%d0%b5%d1%80-%d0%b4%d0%b6%d0%b0%d1%80%d0%bc%d0%b0%d0%bd-%d0%b2%d0%be%d1%81%d0%bf%d0%be%d0%bc%d0%b8%d0%bd%d0%b0%d0%bd%d0%b8%d1%8f/
//      https://quakers.ru/tag/%D0%B4%D0%B6%D0%B0%D1%80%D0%BC%D0%B0%D0%BD%D1%8B/
//  Пришла печальная весть о кончине Питера Джармана. Питер - вместе со своей женой Росвитой - был первым представителем квакеров в России после 60-летнего перерыва (квакерский офис Дорис Уайт был закрыт в Москве в 1931 г.).
//Летом 1991 года Питер и Росвита прибыли в Москву. Формально Питер Джарман был московским корреспондентом британского квакерского еженедельника The Friend («Друг»), советские власти дали аккредитацию ему как журналисту. Питер вспоминал: "В три часа пополудни наш поезд подошёл к платформе Белорусского вокзала в Москве, где нас встретила русская квакерея Татьяна Павлова со своими друзьями. Они помогли нам добраться до нашей съёмной квартиры. «Надеемся, что у вас есть что сказать нам», – сказали встречавшие нас. На что мы вежливо ответили: «Нет, мы приехали слушать»".
//Приблизительно в то же время в Москве открылся и офис Amnesty International - первым директором была квакерея Марджори Фаркухарсон, которая общалась с Джарманами в Москве. И Марджори, и Питер с Росвитой наблюдали исторический спуск красного флага с флагштока над Кремлём и замену его на триколор. Казалось, начинается новая эпоха, думалось, что свобода пришла в страну. Джарманы дали старт Московскому квакерскому собранию, они много ездили по стране: Питер и Росвита приехали ко мне в 1992 году, это была очень интересная встреча.
//  Они уехали из России в 1993 году, передав эстафету новым представителям квакеров. После этого я не раз встречался с Питером и Росвитой. Питер приезжал ко мне. Последний раз мы виделись в Йорке, куда я приезжал по приглашению Питера Джармана для выступления в Йоркском университете.
//Мне было очень интересно говорить с Питером, он во многом участвовал, многое знал. Например, в 1957 году Питер в составе Британской делегации приезжал на Фестиваль молодёжи в Москву. Именно там молодые британцы встретились с оставшимся в СССР манчестерским квакером Артуром Уоттсом. И хотя сам Питер не встречался с Артуром, он поделился со мной уникальным документом - записью разговора его английского товарища с Уоттсом. Этот скрипт я использовал для своей книги "Как квакеры спасали Россию", вышедшей в Москве в 2020 году.
//Питер вместе с Росвитой сделали большое дело - очень много людей в России узнали о квакерах с их помощью. Питера помнят и будут помнить. Спасибо, Питер Джарман.
    )
  )
end People
