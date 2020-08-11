package com.example.beautyscannerandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beautyscannerandroid.model.Category
import kotlinx.android.synthetic.main.category_list_item.view.*

class CategoryListAdapter(var myDataset: List<Category>, val callback: OnCategoryClickListener) :
    RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder>() {

    class CategoryListViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryListAdapter.CategoryListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_list_item, parent, false)

        return CategoryListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        holder.view.categoryTitle.text = myDataset[position].name
        //todo can add here more mappings e.g foto

        holder.view.categoryTitle.setOnClickListener{
            callback.onCategoryClicked(myDataset[position])
        }
    }

    override fun getItemCount() = myDataset.size

}
