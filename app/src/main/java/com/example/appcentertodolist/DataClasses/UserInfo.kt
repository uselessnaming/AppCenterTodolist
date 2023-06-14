package com.example.appcentertodolist.DataClasses

import com.example.appcentertodolist.Task

data class UserInfo(
    val email : String,
    val id : Int,
    val tasks : List<Task>,
    val username : String
)
