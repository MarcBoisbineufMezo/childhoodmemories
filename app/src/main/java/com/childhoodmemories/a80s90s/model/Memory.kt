package com.childhoodmemories.a80s90s.model

data class Memory(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val user: User,
    val category: Category,
)

enum class Category {
    MUSIC, MOVIE, GAME, TOY, FOOD, DRINK, CANDY, TV_SHOW, SPORT
}
