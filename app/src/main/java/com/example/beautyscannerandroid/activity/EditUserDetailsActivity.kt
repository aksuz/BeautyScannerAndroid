package com.example.beautyscannerandroid.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.model.User
import com.example.beautyscannerandroid.viewmodel.CategoryListViewModel
import com.example.beautyscannerandroid.viewmodel.EditUserDetailsViewModel
import kotlinx.android.synthetic.main.activity_category_list.*
import kotlinx.android.synthetic.main.activity_edit_user_details.*

class EditUserDetailsActivity : AppCompatActivity() {
    private val viewModel: EditUserDetailsViewModel by lazy {
        ViewModelProvider(this).get(EditUserDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user_details)


        setupObservers()

//        intent.getLongExtra("savedInstance", 0)
        saveUserInfoButton.setOnClickListener {
            viewModel.updateUserDetails(2L, creareUser())
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

    private fun creareUser(): User {
       return User(
            id = 2L,
            email = editEmail.text.toString(),
            nick = editNick.text.toString(),
            role = "USER"
        )
    }
}