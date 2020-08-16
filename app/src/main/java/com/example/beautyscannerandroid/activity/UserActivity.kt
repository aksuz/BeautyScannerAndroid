package com.example.beautyscannerandroid.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.adapter.UserAdapter
import com.example.beautyscannerandroid.adapter.UserProductsListAdapter
import com.example.beautyscannerandroid.listener.OnUserProductClickListener
import com.example.beautyscannerandroid.model.MyProduct
import com.example.beautyscannerandroid.model.User
import com.example.beautyscannerandroid.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.fragment_user_details.*
import kotlinx.android.synthetic.main.fragment_user_products.*

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        supportActionBar?.title = getString(R.string.user_info)
        viewPager.adapter = UserAdapter(this, supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }
}