package com.example.appcentertodolist

data class Task(
    val deadline: String,
    val description: String,
    val id: Int,
    val isCompleted: Boolean,
    val title: String
)