package com.example.beautyscannerandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beautyscannerandroid.model.Ingredient
import com.example.beautyscannerandroid.network.BeautyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DisplayAllergensListViewModel : ViewModel() {

    val allergenList = MutableLiveData<List<Ingredient>>()

    fun getAllAllergenIngredients() {
        val service = BeautyService.create()
        val call = service.getAllAllergenIngredients()

        call.enqueue(object : Callback<List<Ingredient>> {
            override fun onResponse(
                call: Call<List<Ingredient>>,
                response: Response<List<Ingredient>>
            ) {
                if (response.isSuccessful) {
                    println()
                    allergenList.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Ingredient>>, t: Throwable) {
                println()
            }
        })
    }
}
