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
import com.example.beautyscannerandroid.model.UserRegistration
import com.example.beautyscannerandroid.viewmodel.UserRegisterViewModel
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import kotlinx.android.synthetic.main.activity_user_login.resetDataButton
import kotlinx.android.synthetic.main.activity_user_register.*

class UserRegisterActivity : AppCompatActivity() {
    private val viewModel: UserRegisterViewModel by lazy {
        ViewModelProvider(this).get(UserRegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)
        val sharedPreferences = getSharedPreferences(Constants.SHARED_INFO, Context.MODE_PRIVATE)
        setupObservers()

        resetDataButton.setOnClickListener {
            registerUserName.setText("")
            registerUserEmail.setText("")
            registerUserPassword.setText("")
            registerUserPassword2.setText("")
        }

        registerNewUserButton.setOnClickListener{
            val validationList = mutableListOf<String>()
            validateForm(validationList)

            if (validationList.isNullOrEmpty()) {
                val userRegistration = UserRegistration(
                    nick = registerUserName.text.toString().trim(),
                    email = registerUserEmail.text.toString().trim(),
                    password = registerUserPassword.text.toString().trim()
                )
                viewModel.registerUser(userRegistration)

            } else {
                Toast.makeText(this, validationList.joinToString("\n"), Toast.LENGTH_LONG).show()
            }
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

                Toast.makeText(this, "Konto załozone", Toast.LENGTH_LONG).show()
                val intent = Intent(this, UserActivity::class.java)
                startActivity(intent)
            })
    }

    private fun validateForm(validationList: MutableList<String>) {
        registerUserName.nonEmpty() {
            validationList.add(getString(R.string.validation_nick_not_empty))
        }
        registerUserEmail.nonEmpty() {
            validationList.add(getString(R.string.validation_email_not_empty))
        }
        registerUserEmail.validEmail() {
            validationList.add(getString(R.string.validation_email_not_correct))
        }
        registerUserPassword.nonEmpty() {
            validationList.add(getString(R.string.validation_password_not_empty))
        }
        if (registerUserPassword.text.toString() != registerUserPassword2.text.toString()) {
            validationList.add(getString(R.string.validation_password_match))
        }
    }
}