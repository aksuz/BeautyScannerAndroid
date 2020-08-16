package com.example.beautyscannerandroid.model


data class UserMyProduct(
    val id: Long,
    val myProducts: List<MyProduct>,
    val user: User
)