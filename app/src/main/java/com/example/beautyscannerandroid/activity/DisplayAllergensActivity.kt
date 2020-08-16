package com.example.beautyscannerandroid.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.adapter.DisplayAllergensAdapter
import com.example.beautyscannerandroid.listener.OnAllergenClickListener
import com.example.beautyscannerandroid.model.Ingredient
import com.example.beautyscannerandroid.viewmodel.DisplayAllergensListViewModel
import kotlinx.android.synthetic.main.activity_display_allergen.*

class DisplayAllergensActivity : AppCompatActivity() {
    private val viewModel: DisplayAllergensListViewModel by lazy {
        ViewModelProvider(this).get(DisplayAllergensListViewModel::class.java)
    }
    private val allergenListAdapter: DisplayAllergensAdapter by lazy {
        DisplayAllergensAdapter(
            listOf(),
            getOnAllergenClickListener()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_allergen)
        supportActionBar?.title = getString(R.string.allergens)
        initRecycler()
        setupObservers()
        viewModel.getAllAllergenIngredients()
    }

    private fun initRecycler() {
        ingredientsAllergenList.apply {
            layoutManager = LinearLayoutManager(this@DisplayAllergensActivity)
            adapter = allergenListAdapter
        }
    }

    private fun setupObservers() {
        viewModel.allergenList.observe(
            this,
            Observer { allergens ->
                allergenListAdapter.myDataset = allergens
                allergenListAdapter.notifyDataSetChanged()
            })
    }

    private fun getOnAllergenClickListener(): OnAllergenClickListener {
        return object :
            OnAllergenClickListener {
            override fun onAllergenClicked(ingredient: Ingredient) {
                Toast.makeText(
                    this@DisplayAllergensActivity,
                    "Kliknięto przycisk",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}