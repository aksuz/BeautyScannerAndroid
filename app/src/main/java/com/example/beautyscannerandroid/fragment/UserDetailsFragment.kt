package com.example.beautyscannerandroid.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.model.User
import com.example.beautyscannerandroid.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_details.*


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
//                categoryProductListLoader.visibility = View.GONE
                mapUserToUserDetails(user)
            })
    }

    private fun mapUserToUserDetails(user: User?) {
        nick.text = user!!.nick
        email.text = user!!.email
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    companion object {
        fun newInstance() : UserDetailsFragment = UserDetailsFragment()
    }
}