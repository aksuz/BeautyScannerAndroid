package com.example.beautyscannerandroid.listener

import com.example.beautyscannerandroid.model.Ingredient

interface OnAllergenClickListener {
    fun onAllergenClicked(allergens: Ingredient)
}