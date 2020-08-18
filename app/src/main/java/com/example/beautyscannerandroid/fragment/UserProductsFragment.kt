package com.example.beautyscannerandroid.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.adapter.UserProductsListAdapter
import com.example.beautyscannerandroid.helper.Constants
import com.example.beautyscannerandroid.listener.OnUserProductClickListener
import com.example.beautyscannerandroid.model.MyProduct
import com.example.beautyscannerandroid.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_products.*

class UserProductsFragment : Fragment() {
    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }
    private val userProductListAdapter: UserProductsListAdapter by lazy {
        UserProductsListAdapter(
            listOf(),
            getOnUserProductClickListener()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        val sharedPreferences: SharedPreferences? =
            activity?.getSharedPreferences(Constants.SHARED_INFO, Context.MODE_PRIVATE)
        viewModel.getUserProducts(sharedPreferences!!.getLong(Constants.USER_ID, 0L))
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.userProductList.observe(
            this,
            Observer { userProducts ->
                userProductListAdapter.myDataset = userProducts
                userProductListAdapter.notifyDataSetChanged()
            }
        )
    }

    private fun getOnUserProductClickListener(): OnUserProductClickListener {
        return object :
            OnUserProductClickListener {
            override fun onUserProductClicked(product: MyProduct) {
                val intent = Intent(activity, UserProductsListAdapter::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userProductList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = userProductListAdapter
        }
    }

    companion object {
        fun newInstance(): UserProductsFragment = UserProductsFragment()
    }
}