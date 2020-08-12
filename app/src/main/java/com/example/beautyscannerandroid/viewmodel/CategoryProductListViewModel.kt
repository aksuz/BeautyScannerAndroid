package com.example.beautyscannerandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beautyscannerandroid.model.Product
import com.example.beautyscannerandroid.network.BeautyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryProductListViewModel : ViewModel() {

    val categoryProductList = MutableLiveData<List<Product>>()
    val shouldHideLoader = MutableLiveData<Any>()

    fun getCategoryProducts(categoryId: Long) {
        val service = BeautyService.create()
        val call = service.getCategoryProducts(categoryId)

        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    println()
                    categoryProductList.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                println()
                shouldHideLoader.value = Any()
            }
        })
    }

}
