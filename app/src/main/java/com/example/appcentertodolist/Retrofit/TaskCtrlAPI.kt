package com.example.appcentertodolist.Retrofit

import com.example.appcentertodolist.Data_Classes.PostModel
import com.example.appcentertodolist.Data_Classes.PostResult
import com.example.appcentertodolist.Data_Classes.UpdateTask
import com.example.appcentertodolist.Task
import com.example.appcentertodolist.TaskList
import retrofit2.Call
import retrofit2.http.*

interface TaskCtrlAPI {

    //Todo를 서버에서 받아옴
    @GET("/api/tasks")
    fun getTodo(
        @Query("title") title : String?
    ) : Call<TaskList>
    
    //Todo 서버에 추가
    @POST("/api/tasks")
    @Headers("deadline:application/json",
        "description:application/json",
        "title:application/json")
    fun insertTodo(
        @Body jsonParams : PostModel
    ) : Call<PostResult>
    
    //할 일 id별 조회
    @GET("/api/tasks/{id}")
    fun searchTodo(
        @Path("id") id : Int
    ) : Call<Task>

    //할 일 수정
    @PUT("/api/tasks/{id}")
    suspend fun updateTodo(
        @Path("id") id : Int,
        @Body updateTask : UpdateTask
    ) : Call<Task>

    //Task 삭제
    @DELETE("/api/tasks/{id}")
    fun deleteTask(
        @Path("id") id : Int
    ) : Call<Task> //여기 뭐가 와야 하지,,?

}