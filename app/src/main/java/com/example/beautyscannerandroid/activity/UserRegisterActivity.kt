package com.example.beautyscannerandroid.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.beautyscannerandroid.R
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import kotlinx.android.synthetic.main.activity_user_login.resetDataButton
import kotlinx.android.synthetic.main.activity_user_register.*

class UserRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)

        resetDataButton.setOnClickListener {
            registerUserName.setText("")
            registerUserEmail.setText("")
            registerUserPassword.setText("")
            registerUserPassword2.setText("")
        }

        registerNewUserButton.setOnClickListener{
            val validationList = mutableListOf<String>()
            validateForm(validationList)

            if(validationList.isNullOrEmpty()) {
                Toast.makeText(this, "Konto załozone", Toast.LENGTH_LONG).show()
                val intent = Intent(this, UserActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, validationList.joinToString("\n"), Toast.LENGTH_LONG).show()
            }
        }
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