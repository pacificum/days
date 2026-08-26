package light.days

import java.time.{Month, LocalDate}

import light.pages.Page

object Movies:
  val movies: Seq[ArtWork] = Seq()

  val drafts: Seq[ArtWork] = Seq(
    ArtWork(
      key = "hakuchi",
      name = "Идиот",
      author = "Акира Куросава",
      LocalDate.of(1951, Month.MAY, 23),
      _ => Page.empty
      //японская чёрно-белая драма режиссёра Акиры Куросавы
      //https://ru.wikipedia.org/wiki/%D0%98%D0%B4%D0%B8%D0%BE%D1%82_(%D1%84%D0%B8%D0%BB%D1%8C%D0%BC,_1951)
    ),
    ArtWork(
      key = "johnny_hot_his_gun",
      name = "Джонни взял ружьё",
      desc = "Johnny Got His Gun",
      author = "Далтон Трамбо",
//      author = "Dalton Trumbo",
      LocalDate.of(1939, Month.SEPTEMBER, 3),
      _ => Page.empty
      // An anti-war novel written in 1938 by American novelist Dalton Trumbo and published in September 1939
      // https://en.wikipedia.org/wiki/Johnny_Got_His_Gun
      // Режиссёрский дебют американского сценариста Далтона Трамбо,
      // антивоенный фильм, поставленный им по собственному одноимённому роману (1939).
      // American drama anti-war film written and directed by Dalton Trumbo,
      // in his directorial debut, based on his 1939 novel of the same name,
      // with an uncredited writing collaboration by Luis Buñuel
      // https://ru.wikipedia.org/wiki/%D0%94%D0%B6%D0%BE%D0%BD%D0%BD%D0%B8_%D0%B2%D0%B7%D1%8F%D0%BB_%D1%80%D1%83%D0%B6%D1%8C%D1%91_(%D1%84%D0%B8%D0%BB%D1%8C%D0%BC,_1971)
      // https://en.wikipedia.org/wiki/Johnny_Got_His_Gun_(film)
      // «One» — 4-я песня из студийного альбома группы Metallica …And Justice for All.
      // Текст песни написан под впечатлением от прочтённого Хетфилдом
      // романа Далтона Трамбо «Джонни взял ружьё», рассказывающем историю солдата Первой мировой войны,
      // подорвавшегося на мине и лишившегося конечностей и органов чувств,
      // но тем не менее продолжающего биологически жить и сохранившего способность мыслить
      // в таких условиях. В «One» перед слушателем предстают мысленные кошмарные картины
      // и ощущения человека, попавшего в аналогичную ситуацию —
      // ещё одной из жертв бессмысленных войн.
      // https://ru.wikipedia.org/wiki/One_(%D0%BF%D0%B5%D1%81%D0%BD%D1%8F_Metallica)
      // https://en.wikipedia.org/wiki/One_(Metallica_song)
    )
  )
end Movies
