package com.example.beautyscannerandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.listener.OnCategoryProductClickListener
import com.example.beautyscannerandroid.listener.OnUserProductClickListener
import com.example.beautyscannerandroid.model.MyProduct
import com.example.beautyscannerandroid.model.Product
import kotlinx.android.synthetic.main.item_category_product_list.view.*
import kotlinx.android.synthetic.main.item_user_product_list.view.*

class UserProductsListAdapter (
    var myDataset: List<MyProduct>,
    val callback: OnUserProductClickListener
) :
    RecyclerView.Adapter<UserProductsListAdapter.UserProductListViewHolder>() {

    class UserProductListViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserProductListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_product_list, parent, false)

        return UserProductListViewHolder(
            view
        )
    }

    override fun onBindViewHolder(
        holder: UserProductsListAdapter.UserProductListViewHolder,
        position: Int
    ) {
        holder.view.userProductName.text = myDataset[position].productId.name
        holder.view.userProductProducer.text = myDataset[position].productId.producer.name
        holder.view.userProductOpeningDate.text = myDataset[position].openingDate
        holder.view.userProductExpirationDate.text = myDataset[position].expirationTime
        var picture: String = "https://www.saccon.it/img/coming-soon.jpg"
        if (!myDataset[position].productId.url.isNullOrEmpty()) {
            picture = myDataset[position].productId.url.toString()
        }
        Glide.with(holder.itemView.context).load(picture).into(holder.view.userProductImage)

        holder.view.userProductName.setOnClickListener {
            callback.onUserProductClicked(myDataset[position])
        }
    }

    override fun getItemCount() = myDataset.size

}
