package com.example.beautyscannerandroid.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.helper.Constants
import com.example.beautyscannerandroid.model.User
import com.example.beautyscannerandroid.viewmodel.EditUserDetailsViewModel
import kotlinx.android.synthetic.main.activity_edit_user_details.*

class EditUserDetailsActivity : AppCompatActivity() {
    private val viewModel: EditUserDetailsViewModel by lazy {
        ViewModelProvider(this).get(EditUserDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user_details)

        setupObservers()

        editNick.setText(intent.getStringExtra("userNick"))
        editEmail.setText(intent.getStringExtra("userEmail"))
        saveUserInfoButton.setOnClickListener {
            val sharedPreferences: SharedPreferences? = this.getSharedPreferences(Constants.SHARED_INFO, Context.MODE_PRIVATE)
            viewModel.updateUserDetails(sharedPreferences!!.getLong(Constants.USER_ID, 0L), createUser())
        }
    }

    private fun setupObservers() {
        viewModel.finishActivity.observe(
            this,
            Observer {
                finish()
            })
    }

    private fun createUser(): User {
        val sharedPreferences: SharedPreferences? = this.getSharedPreferences(Constants.SHARED_INFO, Context.MODE_PRIVATE)
        return User(
            id = sharedPreferences!!.getLong(Constants.USER_ID, 0L),
            email = editEmail.text.toString(),
            nick = editNick.text.toString(),
            role = Constants.ROLE_USER
        )
    }
}