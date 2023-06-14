package com.example.appcentertodolist.DataClasses

data class LoginResponseBody (
    val code : Int,
    val msg : String,
    val data : User
)