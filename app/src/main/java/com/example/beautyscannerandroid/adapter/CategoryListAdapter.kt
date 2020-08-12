package com.example.beautyscannerandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.listener.OnCategoryClickListener
import com.example.beautyscannerandroid.model.Category
import kotlinx.android.synthetic.main.item_category_list.view.*

class CategoryListAdapter(var myDataset: List<Category>, val callback: OnCategoryClickListener) :
    RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder>() {

    class CategoryListViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_list, parent, false)

        return CategoryListViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        holder.view.categoryTitle.text = myDataset[position].name
        //todo can add here more mappings e.g foto

        holder.view.categoryTitle.setOnClickListener {
            callback.onCategoryClicked(myDataset[position])
        }
    }

    override fun getItemCount() = myDataset.size

}
