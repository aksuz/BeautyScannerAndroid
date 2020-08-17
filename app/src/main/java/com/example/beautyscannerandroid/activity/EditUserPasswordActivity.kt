package com.example.beautyscannerandroid.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.fragment.UserDetailsFragment
import kotlinx.android.synthetic.main.activity_edit_user_password.*

class EditUserPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user_password)

        saveUserPasswordButton.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }
    }
}