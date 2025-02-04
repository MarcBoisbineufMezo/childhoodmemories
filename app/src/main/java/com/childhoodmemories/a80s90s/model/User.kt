package com.childhoodmemories.a80s90s.model

data class User(
    val id: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
    val avatar: String?,
)