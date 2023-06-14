package com.example.appcentertodolist.Retrofit

import com.example.appcentertodolist.DataClasses.*
import retrofit2.Call
import retrofit2.http.*

interface UserAPI {

    //로그인
    @POST("/api/users/login")
    @Headers("username : application/json",
        "password : application/json")
    fun login(
        @Body jsonParams : PostLoginModel
    ) : Call<LoginResponseBody>
    
    //회원가입
    @POST("/api/users/join")
    @Headers("email : application/json",
        "password : application/json",
        "username : application/json")
    fun addUser(
        @Body jsonParams : PostAddUserModel
    ) : Call<AddUserResponseBody>

    //사용자 정보 수정
    @PUT("/api/users")
    @Headers("email : application/json",
        "password : application/json",
        "username : application/json")
    fun updateUser(
        @Body jsonParams : PostAddUserModel
    ) : Call<String>

    //사용자 삭제
    @DELETE("/api/users/{id}")
    fun deleteUser(
        @Path("id") id : Int
    ) : Call<String>

    //사용자 조회 (관리자 권한)
    @GET("/api/users/admin")
    fun searchUser(
    ) : Call<UserList>

    //본인 정보 확인
    @GET("/api/users/info")
    fun searchMyInfo(
    ) : Call<String>
}