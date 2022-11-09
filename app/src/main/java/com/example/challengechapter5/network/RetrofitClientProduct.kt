package com.example.challengechapter5.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientProduct {
    private const val BASE_URL = "https://makeup-api.herokuapp.com/"

    val instance : RestfulAPIProduct by lazy {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        retrofit.create(RestfulAPIProduct::class.java)
    }
}