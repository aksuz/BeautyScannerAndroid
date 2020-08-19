package com.example.beautyscannerandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beautyscannerandroid.model.Category
import com.example.beautyscannerandroid.network.BeautyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryListViewModel : ViewModel() {

    val categoryList = MutableLiveData<List<Category>>()
    val shouldHideLoader = MutableLiveData<Any>()

    fun getCategories() {
        val service = BeautyService.create()
        val call = service.getCategories()

        call.enqueue(object : Callback<List<Category>> {
            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                if (response.isSuccessful) {
                    println()
                    categoryList.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                println()
                shouldHideLoader.value = Any()
            }
        })
    }
}