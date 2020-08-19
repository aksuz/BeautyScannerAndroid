package com.example.beautyscannerandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beautyscannerandroid.model.MyProduct
import com.example.beautyscannerandroid.model.Product
import com.example.beautyscannerandroid.network.BeautyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProductToFavouritesViewModel : ViewModel() {

    val productDetails = MutableLiveData<Product>()
    val myProductDetails = MutableLiveData<MyProduct>()
    val shouldHideLoader = MutableLiveData<Any>()

    fun getProductDetails(productId: Long) {
        val service = BeautyService.create()
        val call = service.getProductDetailsById(productId)

        call.enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful) {
                    println()
                    productDetails.value = response.body()
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                println()
                shouldHideLoader.value = Any()
            }
        })
    }

    fun addUserProduct(userId: Long, myProduct: MyProduct) {
        val service = BeautyService.create()
        val call = service.addUserProduct(userId, myProduct)

        call.enqueue(object : Callback<MyProduct> {
            override fun onResponse(call: Call<MyProduct>, response: Response<MyProduct>) {
                if (response.isSuccessful) {
                    println()
                    myProductDetails.value = response.body()
                }
            }

            override fun onFailure(call: Call<MyProduct>, t: Throwable) {
                println()
                shouldHideLoader.value = Any()
            }
        })
    }
}
