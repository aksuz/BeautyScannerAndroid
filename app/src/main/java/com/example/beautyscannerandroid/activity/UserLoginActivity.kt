package com.example.beautyscannerandroid.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.helper.Constants
import com.example.beautyscannerandroid.viewmodel.UserLoginViewModel
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import kotlinx.android.synthetic.main.activity_user_login.*

class UserLoginActivity : AppCompatActivity() {
    private val viewModel: UserLoginViewModel by lazy {
        ViewModelProvider(this).get(UserLoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
        val sharedPreferences = getSharedPreferences(Constants.SHARED_INFO, Context.MODE_PRIVATE)
        setupObservers()

        resetDataButton.setOnClickListener {
            loginUserName.setText("")
            loginUserPassword.setText("")
        }
        loginButton.setOnClickListener {
            val validationList = mutableListOf<String>()
            validateForm(validationList)

            if (validationList.isNullOrEmpty()) {
                viewModel.loginUser(loginUserName.text.toString().trim(), loginUserPassword.text.toString().trim())


            } else {
                Toast.makeText(this, validationList.joinToString("\n"), Toast.LENGTH_LONG).show()
            }
        }
        registerNewUserButton.setOnClickListener {
            Toast.makeText(this, "załóż nowe konto", Toast.LENGTH_LONG).show()
            val intent = Intent(this, UserRegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupObservers() {
        viewModel.finishActivity.observe(
            this,
            Observer {
                val sharedPreferences: SharedPreferences = this.getSharedPreferences(Constants.SHARED_INFO,Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString(Constants.USER_NICK, viewModel.loggedInUser.value?.nick).commit()
                editor.putString(Constants.USER_EMAIL, viewModel.loggedInUser.value?.email).commit()
                viewModel.loggedInUser.value?.id?.let { it1 -> editor.putLong(Constants.USER_ID, it1).commit() }

                Toast.makeText(this, "Zalogowano", Toast.LENGTH_LONG).show()
                val intent = Intent(this, UserActivity::class.java)
                startActivity(intent)
            })
    }
    private fun validateForm(validationList: MutableList<String>) {
        loginUserName.nonEmpty() {
            validationList.add(getString(R.string.validation_nick_not_empty))
        }
        loginUserPassword.nonEmpty() {
            validationList.add(getString(R.string.validation_password_not_empty))
        }
    }


}
