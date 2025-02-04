package com.childhoodmemories.a80s90s.data

import com.childhoodmemories.a80s90s.data.database.MemorieDao
import com.childhoodmemories.a80s90s.model.Category
import com.childhoodmemories.a80s90s.model.Memory

object MemoryRepository {

    private val memorieDao = MemorieDao()

    suspend fun loadMemories(): List<Memory> {
        // TODO improve with suspend
        return memorieDao.memories + mockMemoriesList.sortedByDescending { it.id }
    }
}

val memory1 = Memory(
    id = 1,
    title = "My first bike",
    description = "I remember my first bike, it was a red bike with white wheels",
    image = "https://backbonebmx.com/cdn/shop/products/skyway-ta-replica-pro-bmx-bike-20-714392.jpg?v=1705878832",
    user = emma,
    category = Category.SPORT,
)
val memory2 = Memory(
    id = 2,
    title = "My first video game",
    description = "I remember my first video game, it was a Super Mario Bros game",
    image = "https://img.odcdn.com.br/wp-content/uploads/2015/10/20151019123416.jpg",
    user = liam,
    category = Category.GAME,
)
val memory3 = Memory(
    id = 3,
    title = "My first computer",
    description = "I remember my first computer, it was a Commodore 64",
    image = "https://cdn-s-www.lejsl.com/images/3771551A-68B2-444B-8816-0B553CDA1443/NW_raw/le-commodore-64-avec-ecran-et-lecteur-de-disquette-a-connu-un-succes-colossal-bill-bertram-via-wikimedia-commons-1502702423.jpg",
    user = sophia,
    category = Category.GAME,
)
val memory4 = Memory(
    id = 4,
    title = "My first toy",
    description = "I remember my first toy, it was a Barbie doll",
    image = "https://images.mattel.net/images/w_720,f_auto,c_scale/shop-emea-prod/files/oikkrmrxph4zstqhp9gk_937febc8-1129-480f-9240-1344083fc64b/barbie-barbie-metiers-barbie-pediatre-gkh23-fr-fr.jpg",
    user = isabella,
    category = Category.TOY,
)
val memory5 = Memory(
    id = 5,
    title = "My first car",
    description = "I remember my first car, it was a Ford Mustang",
    image = "https://www.velocityrestorations.com/assets/media/1289r-velocity-ford-mustang-fastback-in-monterey-01-1920x914.webp",
    user = mason,
    category = Category.SPORT,
)
val memory6 = Memory(
    id = 6,
    title = "My first movie",
    description = "I remember my first movie, it was Home Alone",
    image = "https://geekbecois.com/wp-content/uploads/2019/12/home-alone-logo.jpg",
    user = ethan,
    category = Category.MOVIE,
)
val memory7 = Memory(
    id = 7,
    title = "My first TV show",
    description = "I remember my first TV show, it was The Simpsons",
    image = "https://i.ebayimg.com/images/g/D6sAAOSwIbpcSU4P/s-l1600.webp",
    user = emma,
    category = Category.TV_SHOW,
)
val memory8 = Memory(
    id = 8,
    title = "My first candy",
    description = "I remember my first candy, it was a lollipop",
    image = "https://preview.redd.it/k9tb5u9f0ym91.jpg?auto=webp&s=f4bb07f036ddd3148c8395ab3be88b025d1789d8",
    user = liam,
    category = Category.CANDY,
)
val memory9 = Memory(
    id = 9,
    title = "My first drink",
    description = "I remember my first drink, it was a Coca-Cola",
    image = "https://eproshopping.cloud/media/4d57bc82cf40fad9d673d50335c51e96f00faa7d/produit/23521e98bfd38d34aa1abacb8a60ca2a4bbad5fc-lg.jpg",
    user = emma,
    category = Category.DRINK,
)
val memory10 = Memory(
    id = 10,
    title = "My first food",
    description = "I remember my first food, it was a hamburger",
    image = "https://media.gettyimages.com/id/2179715326/zh/%E7%85%A7%E7%89%87/a-quarter-pounder-with-cheese-fries-and-a-drink-arranged-at-a-mcdonalds-restaurant-in-el.jpg?s=2048x2048&w=gi&k=20&c=0A0QQsV7qykYhYbFTDwoJ6bJCqw_U-yknSEWp5gQ8_4=",
    user = isabella,
    category = Category.FOOD,
)
val memory11 = Memory(
    id = 11,
    title = "My first music",
    description = "I remember my first music, it was a Michael Jackson song",
    image = "https://www.lodj.ma/photo/art/grande/76218771-54062265.jpg?v=1698658100",
    user = emma,
    category = Category.MUSIC,
)
val memory12 = Memory(
    id = 12,
    title = "Best player ever",
    description = "The goat is here. The best player have never seen",
    image = "https://www.superception.fr/wp-content/uploads/2020/05/Michael-Jordan-by-Walter-Iooss-Jr.jpg",
    user = ethan,
    category = Category.SPORT,
)
val mockMemoriesList = listOf(
    memory1,
    memory2,
    memory3,
    memory4,
    memory5,
    memory6,
    memory7,
    memory8,
    memory9,
    memory10,
    memory11,
    memory12
)