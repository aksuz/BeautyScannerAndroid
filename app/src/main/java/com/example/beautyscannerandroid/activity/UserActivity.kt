package com.example.beautyscannerandroid.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.adapter.UserAdapter
import com.example.beautyscannerandroid.adapter.UserProductsListAdapter
import com.example.beautyscannerandroid.listener.OnUserProductClickListener
import com.example.beautyscannerandroid.model.MyProduct
import com.example.beautyscannerandroid.model.User
import com.example.beautyscannerandroid.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.fragment_user_details.*
import kotlinx.android.synthetic.main.fragment_user_products.*

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        supportActionBar?.title = getString(R.string.user_info)
        viewPager.adapter = UserAdapter(this, supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

        //todo after implementing LOG IN functionality change this value!!
//        viewModel.getUserDetails(2L)
//        viewModel.getUserProducts(2L)
//        viewModel.getUserAllergens(2L)
//        initRecycler()
//        setupObservers()
    }

//    private fun initRecycler() {
//        userProductList.apply {
//            layoutManager = LinearLayoutManager(this@UserActivity)
//            adapter = userProductListAdapter
//        }
//    }
//
//    private fun getOnUserProductClickListener(): OnUserProductClickListener {
//        return object :
//            OnUserProductClickListener {
//            override fun onUserProductClicked(product: MyProduct) {
//                val intent = Intent(this@UserActivity, UserActivity::class.java)
////                intent.putExtra("productId", product.id)
//                startActivity(intent)
//            }
//        }
//
//    }

//    private fun setupObservers() {
//        viewModel.userDetails.observe(
//            this,
//            Observer { user ->
////                categoryProductListLoader.visibility = View.GONE
//                mapUserToUserDetails(user)
//            })
//        viewModel.userProductList.observe(
//            this,
//            Observer {userProducts ->
//                userProductListAdapter.myDataset = userProducts
//                userProductListAdapter.notifyDataSetChanged()
//            }
//        )
//    }
//
//
//
//    private fun mapUserToUserDetails(user: User?) {
//        nick.text = user!!.nick
//        email.text = user!!.email
//    }
//
//    private fun mapUserToUserProducts(userProducts: List<MyProduct>?) {
//        TODO("Not yet implemented")
//    }

}