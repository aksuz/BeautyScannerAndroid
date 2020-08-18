package com.example.beautyscannerandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beautyscannerandroid.model.UserPassword
import com.example.beautyscannerandroid.network.BeautyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditUserPasswordViewModel : ViewModel() {

    val finishActivity = MutableLiveData<Any>()

    fun updateUserPassword(userId: Long, userPassword: String) {
        val service = BeautyService.create()
        val call = service.updateUserDetails(userId, userPassword)

        call.enqueue(object : Callback<UserPassword> {
            override fun onResponse(call: Call<UserPassword>, response: Response<UserPassword>) {
                if (response.isSuccessful) {
                    println()
                    finishActivity.value = Any()
                }
            }

            override fun onFailure(call: Call<UserPassword>, t: Throwable) {
                println()
            }
        })
    }
}