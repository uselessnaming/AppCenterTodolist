package com.example.appcentertodolist.DataClasses

data class User(
    val id: Int,
    val createdAt: String,
    val username: String,
    val email: String,
    val jwtToken: String
)