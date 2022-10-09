package com.example.challengechapter5.network

import com.example.challengechapter5.model.ResponseDataProductItem
import retrofit2.Call
import retrofit2.http.GET

interface RestfulAPIProduct {
    @GET("api/v1/products.json?brand=maybelline")
    fun getAllFilm() : Call<List<ResponseDataProductItem>>
}