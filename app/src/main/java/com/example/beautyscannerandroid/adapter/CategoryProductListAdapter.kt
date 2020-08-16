package com.example.beautyscannerandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.listener.OnCategoryProductClickListener
import com.example.beautyscannerandroid.model.Product
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.item_category_product_list.view.*

class CategoryProductListAdapter(
    var myDataset: List<Product>,
    val callback: OnCategoryProductClickListener
) :
    RecyclerView.Adapter<CategoryProductListAdapter.CategoryProductListViewHolder>() {

    class CategoryProductListViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryProductListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_product_list, parent, false)

        return CategoryProductListViewHolder(
            view
        )
    }

    override fun onBindViewHolder(
        holder: CategoryProductListAdapter.CategoryProductListViewHolder,
        position: Int
    ) {
        holder.view.productTitle.text = myDataset[position].name

        var picture: String = "https://www.saccon.it/img/coming-soon.jpg"
        if (!myDataset[position].url.isNullOrEmpty()) {
            picture = myDataset[position].url.toString()
        }
        Glide.with(holder.itemView.context).load(picture).into(holder.view.productImage)

        holder.view.productTitle.setOnClickListener {
            callback.onCategoryProductClicked(myDataset[position])
        }
    }

    override fun getItemCount() = myDataset.size

}
