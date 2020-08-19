package com.example.beautyscannerandroid.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.helper.Constants
import com.example.beautyscannerandroid.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences: SharedPreferences? =
            this.getSharedPreferences(Constants.SHARED_INFO, Context.MODE_PRIVATE)
        if (isUserLoggedIn(sharedPreferences)) {
            welcomeText.text =
                getString(R.string.welcome) + sharedPreferences!!.getString(Constants.USER_NICK, "")
                    ?.toUpperCase()
            userInfoButton.text = getString(R.string.user_info)
        }

        scannerButton.setOnClickListener {
            Toast.makeText(this, "Kliknięto przycisk", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ScannerActivity::class.java)
            startActivity(intent)
        }
        categoriesButton.setOnClickListener {
            val intent = Intent(this, CategoryListActivity::class.java)
            startActivity(intent)
        }

        userInfoButton.setOnClickListener {
            if (isUserLoggedIn(sharedPreferences)) {
                val intent = Intent(this, UserActivity::class.java)
                startActivity(intent)
            } else {

                val intent = Intent(this, UserLoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun isUserLoggedIn(sharedPreferences: SharedPreferences?) =
        sharedPreferences!!.getString(Constants.USER_NICK, "")!!.isNotEmpty()


}