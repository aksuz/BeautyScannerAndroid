package com.example.beautyscannerandroid.network

import com.example.beautyscannerandroid.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface BeautyService {

    @GET("/api/categories")
    fun getCategories(): Call<List<Category>>

    @GET("/api/products/categoryId/{id}")
    fun getCategoryProducts(@Path("id") id: Long): Call<List<Product>>

    @GET("/api/products/{id}")
    fun getProductDetailsById(@Path("id") id: Long): Call<Product>

    @GET("/api/products/barcode/{barcode}")
    fun getProductDetailsByBarcode(@Path("barcode") barcode: String): Call<Product>

    @GET("/api/users/{id}")
    fun getUserDetailsById(@Path("id") id: Long): Call<User>

    @GET("/api/users/{id}/userProducts")
    fun getUserProductsByUserId(@Path("id") id: Long): Call<List<MyProduct>>

    @GET("/api/users/{id}/userAllergens")
    fun getUserAllergensByUserId(@Path("id") id: Long): Call<List<Ingredient>>

    @GET("/api/ingredients/onlyAllergen")
    fun getAllAllergenIngredients(): Call<List<Ingredient>>

//    @PUT("/api/users/update/{id}")
//    fun updateUserDetails(@Path("id") id: Long, @Body User user): Call<ResponseBody>


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