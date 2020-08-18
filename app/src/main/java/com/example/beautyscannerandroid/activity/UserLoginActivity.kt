package com.example.beautyscannerandroid.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.beautyscannerandroid.R
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import kotlinx.android.synthetic.main.activity_user_login.*

class UserLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        resetDataButton.setOnClickListener {
            loginUserName.setText("")
            loginUserPassword.setText("")
        }

        loginButton.setOnClickListener {
            val user_name = loginUserName.text;
            val password = loginUserPassword.text;
            Toast.makeText(this, user_name, Toast.LENGTH_LONG).show()
            //todo send data to WS to login
            val validationList = mutableListOf<String>()
            validateForm(validationList)
            if (validationList.isNullOrEmpty()) {
                Toast.makeText(this, "Zalogowano", Toast.LENGTH_LONG).show()
                val intent = Intent(this, UserActivity::class.java)
                startActivity(intent)
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

    private fun validateForm(validationList: MutableList<String>) {
        loginUserName.nonEmpty() {
            validationList.add(getString(R.string.validation_nick_not_empty))
        }
        loginUserPassword.nonEmpty() {
            validationList.add(getString(R.string.validation_password_not_empty))
        }
    }
}
