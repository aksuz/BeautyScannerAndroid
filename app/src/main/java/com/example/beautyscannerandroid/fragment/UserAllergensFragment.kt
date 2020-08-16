package com.example.beautyscannerandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.adapter.UserAllergenListAdapter
import com.example.beautyscannerandroid.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_allergens.*
import kotlinx.android.synthetic.main.fragment_user_products.*

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
        viewModel.getUserAllergens(2L)
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_allergens, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAllergenList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = userAllergenListAdapter
        }
    }

    companion object {
        fun newInstance() : UserAllergensFragment = UserAllergensFragment()
    }
}