package com.example.beautyscannerandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.model.Ingredient
import kotlinx.android.synthetic.main.item_user_allergen_list.view.*

class UserAllergenListAdapter(
    var myDataset: List<Ingredient>
) :
    RecyclerView.Adapter<UserAllergenListAdapter.UserAllergenListViewHolder>() {

    class UserAllergenListViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserAllergenListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_allergen_list, parent, false)

        return UserAllergenListViewHolder(
            view
        )
    }

    override fun onBindViewHolder(
        holder: UserAllergenListAdapter.UserAllergenListViewHolder,
        position: Int
    ) {
        holder.view.allergensTitle.text = myDataset[position].name

    }

    override fun getItemCount() = myDataset.size

}