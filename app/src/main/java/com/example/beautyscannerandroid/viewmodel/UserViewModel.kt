package com.example.beautyscannerandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beautyscannerandroid.model.Ingredient
import com.example.beautyscannerandroid.model.MyProduct
import com.example.beautyscannerandroid.model.User
import com.example.beautyscannerandroid.network.BeautyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    val userDetails = MutableLiveData<User>()
    val userAllergenList = MutableLiveData<List<Ingredient>>()
    val userProductList = MutableLiveData<List<MyProduct>>()
    var isNoContentResponse: Boolean = false

    fun getUserDetails(userId: Long) {
        val service = BeautyService.create()
        val call = service.getUserDetailsById(userId)

        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                println()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    println()
                    if(response.code() == 204)  {
                        isNoContentResponse = true
                    } else {
                        userDetails.value = response.body()
                    }
                }
            }
        })
    }

    fun getUserProducts(userId: Long) {
        val service = BeautyService.create()
        val call = service.getUserProductsByUserId(userId)

        call.enqueue(object : Callback<List<MyProduct>> {
            override fun onFailure(call: Call<List<MyProduct>>, t: Throwable) {
                println()
            }

            override fun onResponse(
                call: Call<List<MyProduct>>,
                response: Response<List<MyProduct>>
            ) {
                if (response.isSuccessful) {
                    println()
                    userProductList.value = response.body()
                }
            }
        })
    }

    fun getUserAllergens(userId: Long) {
        val service = BeautyService.create()
        val call = service.getUserAllergensByUserId(userId)

        call.enqueue(object : Callback<List<Ingredient>> {
            override fun onFailure(call: Call<List<Ingredient>>, t: Throwable) {
                println()
            }

            override fun onResponse(
                call: Call<List<Ingredient>>,
                response: Response<List<Ingredient>>
            ) {
                if (response.isSuccessful) {
                    println()
                    userAllergenList.value = response.body()
                }
            }
        })
    }

}