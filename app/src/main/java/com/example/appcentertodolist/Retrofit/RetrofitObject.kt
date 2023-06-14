package com.example.appcentertodolist.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    private const val BASE_URL = "https://jh-todo.inuappcenter.kr"

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getTaskRetrofitService() : TaskCtrlAPI{
        return getRetrofit().create(TaskCtrlAPI::class.java)
    }

    fun getUserRetrofitService() : UserAPI{
        return getRetrofit().create(UserAPI::class.java)
    }
}