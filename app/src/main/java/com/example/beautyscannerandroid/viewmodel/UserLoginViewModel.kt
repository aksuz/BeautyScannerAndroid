package com.example.beautyscannerandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beautyscannerandroid.model.User
import com.example.beautyscannerandroid.network.BeautyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserLoginViewModel : ViewModel() {

    val loggedInUser = MutableLiveData<User>()
    val finishActivity = MutableLiveData<Any>()

    fun loginUser(nick: String, password: String) {
        val service = BeautyService.create()
        val call = service.loginUser(nick, password)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    loggedInUser.value = response.body()
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
