package com.example.beautyscannerandroid.fragment

import android.content.Intent
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

//        addAllergenButton.setOnClickListener(this)
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
        fun newInstance() : UserAllergensFragment = UserAllergensFragment()
    }
}