package com.example.challengechapter5.network

import com.example.challengechapter5.model.ResponseDataUserItem
import com.example.challengechapter5.model.User
import retrofit2.Call
import retrofit2.http.*

interface RestfulAPIUser {
    @GET("users")
    fun getAllUser() : Call<List<ResponseDataUserItem>>

    @POST("users")
    fun addUser(@Body request : User) : Call<ResponseDataUserItem>

    @PUT("users/{id}")
    fun putUser(@Path("id") id:Int, @Body request: User) : Call<List<ResponseDataUserItem>>
}