package com.example.beautyscannerandroid.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.adapter.CategoryProductListAdapter
import com.example.beautyscannerandroid.helper.Constants
import com.example.beautyscannerandroid.listener.OnCategoryProductClickListener
import com.example.beautyscannerandroid.model.Product
import com.example.beautyscannerandroid.viewmodel.CategoryProductListViewModel
import kotlinx.android.synthetic.main.activity_category_product_list.*

class CategoryProductListActivity : AppCompatActivity() {
    private val viewModel: CategoryProductListViewModel by lazy {
        ViewModelProvider(this).get(CategoryProductListViewModel::class.java)
    }

    private val categoryProductListAdapter: CategoryProductListAdapter by lazy {
        CategoryProductListAdapter(
            listOf(),
            getOnProductClickListener()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_product_list)
        supportActionBar?.title = getString(R.string.products)
        initRecycler()
        setupObservers()
        categoryProductListLoader.visibility = View.VISIBLE
        viewModel.getCategoryProducts(intent.getLongExtra(Constants.CATEGORY_ID, 0))
    }

    private fun initRecycler() {
        categoryProductList.apply {
            layoutManager = LinearLayoutManager(this@CategoryProductListActivity)
            adapter = categoryProductListAdapter
        }
    }

    private fun setupObservers() {
        viewModel.categoryProductList.observe(
            this,
            Observer { products ->
                categoryProductListLoader.visibility = View.GONE
                categoryProductListAdapter.myDataset = products
                categoryProductListAdapter.notifyDataSetChanged()
            })
        viewModel.shouldHideLoader.observe(
            this,
            Observer {
                categoryProductListLoader.visibility = View.GONE
            }
        )
    }

    private fun getOnProductClickListener(): OnCategoryProductClickListener {
        return object :
            OnCategoryProductClickListener {
            override fun onCategoryProductClicked(product: Product) {
                val intent =
                    Intent(this@CategoryProductListActivity, ProductDetailsActivity::class.java)
                intent.putExtra(Constants.PRODUCT_ID, product.id)
                startActivity(intent)
            }
        }

    }
}

