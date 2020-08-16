package com.example.beautyscannerandroid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        supportActionBar?.title = getString(R.string.user_info)
        viewPager.adapter = UserAdapter(this, supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }
}