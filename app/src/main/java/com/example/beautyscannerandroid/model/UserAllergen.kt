package com.example.beautyscannerandroid.model

data class UserAllergen (
    val id: Long,
    val allergens: List<Ingredient>,
    val user: User
)