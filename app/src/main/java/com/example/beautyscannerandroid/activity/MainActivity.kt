package com.example.beautyscannerandroid.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.beautyscannerandroid.viewmodel.MainViewModel
import com.example.beautyscannerandroid.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scanner.setOnClickListener {
            Toast.makeText(this, "Kliknięto przycisk", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ScannerActivity::class.java)
            startActivity(intent)
        }
        categories.setOnClickListener {
            val intent = Intent(this, CategoryListActivity::class.java)
            startActivity(intent)
        }

        userInfo.setOnClickListener {
            val intent = Intent(this, UserLoginActivity::class.java)
//            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }
    }


}