package com.example.beautyscannerandroid.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.adapter.CategoryListAdapter
import com.example.beautyscannerandroid.helper.Constants
import com.example.beautyscannerandroid.listener.OnCategoryClickListener
import com.example.beautyscannerandroid.model.Category
import com.example.beautyscannerandroid.viewmodel.CategoryListViewModel
import kotlinx.android.synthetic.main.activity_category_list.*

class CategoryListActivity : AppCompatActivity() {
    private val viewModel: CategoryListViewModel by lazy {
        ViewModelProvider(this).get(CategoryListViewModel::class.java)
    }
    private val categoryListAdapter: CategoryListAdapter by lazy {
        CategoryListAdapter(
            listOf(),
            getOnCategoryClickListener()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)
        supportActionBar?.title = getString(R.string.categories)
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

    private fun setupObservers() {
        viewModel.categoryList.observe(
            this,
            Observer { categories ->
                categoryListLoader.visibility = View.GONE
                categoryListAdapter.myDataset = categories
                categoryListAdapter.notifyDataSetChanged()
            })
        viewModel.shouldHideLoader.observe(
            this,
            Observer {
                categoryListLoader.visibility = View.GONE
                //todo info dla usera że nie zwracamy listy
                //todo if connection fail inform categotyListLoader
            }
        )
    }

    private fun getOnCategoryClickListener(): OnCategoryClickListener {
        return object :
            OnCategoryClickListener {
            override fun onCategoryClicked(category: Category) {
                val intent =
                    Intent(this@CategoryListActivity, CategoryProductListActivity::class.java)
                intent.putExtra(Constants.CATEGORY_ID, category.id)
                startActivity(intent)
            }
        }
    }
}