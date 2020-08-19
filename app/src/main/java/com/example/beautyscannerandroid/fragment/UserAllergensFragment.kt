package com.example.beautyscannerandroid.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.activity.DisplayAllergensActivity
import com.example.beautyscannerandroid.adapter.UserAllergenListAdapter
import com.example.beautyscannerandroid.helper.Constants
import com.example.beautyscannerandroid.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_allergens.*
import kotlinx.android.synthetic.main.fragment_user_allergens.view.*

class UserAllergensFragment : Fragment() {
    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }
    private val userAllergenListAdapter: UserAllergenListAdapter by lazy {
        UserAllergenListAdapter(
            listOf()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        val sharedPreferences: SharedPreferences? =
            activity?.getSharedPreferences(Constants.SHARED_INFO, Context.MODE_PRIVATE)
        viewModel.getUserAllergens(sharedPreferences!!.getLong(Constants.USER_ID, 0L))
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.userAllergenList.observe(
            this,
            Observer { userAllergens ->
                userAllergenListAdapter.myDataset = userAllergens
                userAllergenListAdapter.notifyDataSetChanged()
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_user_allergens, container, false)

        view.addAllergenButton.setOnClickListener { view ->
            Toast.makeText(activity, "Kliknięto przycisk", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, DisplayAllergensActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAllergenList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = userAllergenListAdapter
        }
    }

    companion object {
        fun newInstance(): UserAllergensFragment = UserAllergensFragment()
    }
}