package com.example.beautyscannerandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beautyscannerandroid.model.Product
import com.example.beautyscannerandroid.model.User
import com.example.beautyscannerandroid.network.BeautyService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Path

class EditUserDetailsViewModel : ViewModel() {

    val finishActivity = MutableLiveData<Any>()

    fun updateUserDetails(userId: Long, user: User) {
        val service = BeautyService.create()
        val call = service.updateUserDetails(userId, user)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    println()
                    finishActivity.value = Any()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                println()
            }
        })
    }
}