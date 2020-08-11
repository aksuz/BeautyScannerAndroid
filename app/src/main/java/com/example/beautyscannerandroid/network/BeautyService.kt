package com.example.beautyscannerandroid.network

import com.example.beautyscannerandroid.model.Category
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BeautyService {

    @GET("/api/categories")
    fun getCategories(): Call<List<Category>>
    //todo more methods for responses

    companion object {
        fun create(): BeautyService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl("http://10.1.185.174:8080")
                .build()

            return retrofit.create(BeautyService::class.java)
        }
    }
}