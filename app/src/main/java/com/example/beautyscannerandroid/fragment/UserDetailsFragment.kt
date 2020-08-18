package com.example.beautyscannerandroid.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.activity.EditUserDetailsActivity
import com.example.beautyscannerandroid.activity.EditUserPasswordActivity
import com.example.beautyscannerandroid.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_details.*
import kotlinx.android.synthetic.main.fragment_user_details.view.*


class UserDetailsFragment : Fragment() {
    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUserDetails(2L)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.userDetails.observe(
            this,
            Observer { user ->
                nick.text = user!!.nick
                email.text = user!!.email
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_user_details, container, false)

        view.editUserInfoButton.setOnClickListener { view ->
            val intent = Intent(activity, EditUserDetailsActivity::class.java)
            intent.putExtra("userNick", nick.text)
            intent.putExtra("userEmail", email.text)
            startActivityForResult(intent, 1000)
        }
        view.editUserPasswordButton.setOnClickListener { view ->
            val intent = Intent(activity, EditUserPasswordActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            viewModel.getUserDetails(2L)
        }
    }

    companion object {
        fun newInstance(): UserDetailsFragment = UserDetailsFragment()
    }
}