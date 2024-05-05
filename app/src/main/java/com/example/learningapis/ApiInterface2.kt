package com.example.learningapis

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface2 {
    @GET("recipes")
    fun getInfo():Call<MySecData>
}