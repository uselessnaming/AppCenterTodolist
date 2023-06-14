package com.example.appcentertodolist.Data_Classes

data class ResponseModel(
    val code : Int,
    val data : List<Any>,
    val msg : String
)
