package com.example.beautyscannerandroid.model

data class User (
    val id: Long,
    val nick: String,
    val email: String,
    val role: String,
    val allergens: List<Ingredient>,
    val myProducts: List<MyProduct>
)