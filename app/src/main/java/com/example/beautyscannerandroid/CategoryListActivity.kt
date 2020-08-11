package com.example.beautyscannerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beautyscannerandroid.model.Category
import kotlinx.android.synthetic.main.activity_category_list.*

class CategoryListActivity : AppCompatActivity() {
    private val viewModel: CategoryListViewModel by lazy {
        ViewModelProvider(this).get(CategoryListViewModel::class.java)
    }
    private val categoryListAdapter: CategoryListAdapter by lazy {
        CategoryListAdapter(listOf(), getOnCategoryClickListener())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)
        supportActionBar?.title = getString(R.string.the_categories)
        initRecycler()
        setupObservers()
        categoryListLoader.visibility = View.VISIBLE
        viewModel.getCategories()
    }

    private fun initRecycler() {
        categoryList.apply {
            layoutManager = LinearLayoutManager(this@CategoryListActivity)
            adapter = categoryListAdapter
        }
    }

    fun setupObservers() {
        viewModel.categoryList.observe(
            this,
            Observer { categories ->
                categoryListLoader.visibility = View.GONE
                categoryListAdapter.myDataset = categories
                categoryListAdapter.notifyDataSetChanged()
            })
        viewModel.shouldHideLoader.observe(
            this,
            Observer{
                categoryListLoader.visibility = View.GONE
                //todo info dla usera że nie zwracamy listy
                //todo if connection fail inform categotyListLoader
            }
        )
    }

    fun getOnCategoryClickListener(): OnCategoryClickListener {
        return object: OnCategoryClickListener{
            override fun onCategoryClicked(category: Category) {
                Toast.makeText(this@CategoryListActivity, category.name, Toast.LENGTH_SHORT).show()
                //todo new activity, intent + category (intent.putExtra) i tam nastepne query gdzie category bedzie parametrem query
            }
        }
    }
}