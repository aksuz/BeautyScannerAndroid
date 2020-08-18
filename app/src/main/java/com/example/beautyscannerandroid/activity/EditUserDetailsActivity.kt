package com.example.beautyscannerandroid.activity

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
            viewModel.updateUserDetails(2L, createUser())
            this.finish()
        }
//shared preferences - info o userze w calej
    }

    private fun setupObservers() {
        viewModel.finishActivity.observe(
            this,
            Observer {
                finish()
            })
    }

    private fun createUser(): User {
        return User(
            id = 2L,
            email = editEmail.text.toString(),
            nick = editNick.text.toString(),
            role = Constants.ROLE_USER
        )
    }
}