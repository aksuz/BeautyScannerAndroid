package com.example.beautyscannerandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.listener.OnAllergenClickListener
import com.example.beautyscannerandroid.model.Ingredient
import kotlinx.android.synthetic.main.item_ingredients_allergen_list.view.*

class DisplayAllergensAdapter(
    var myDataset: List<Ingredient>,
    val callback: OnAllergenClickListener
) :
    RecyclerView.Adapter<DisplayAllergensAdapter.AllergenListViewHolder>() {

    class AllergenListViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllergenListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ingredients_allergen_list, parent, false)

        return AllergenListViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: AllergenListViewHolder, position: Int) {
        holder.view.allergenIngredientTitle.text = myDataset[position].name

        holder.view.allergenIngredientTitle.setOnClickListener {
            callback.onAllergenClicked(myDataset[position])
        }
    }

    override fun getItemCount() = myDataset.size

}