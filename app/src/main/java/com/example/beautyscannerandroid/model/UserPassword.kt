package com.example.beautyscannerandroid.model

data class UserPassword (
    val id: Long,
    val password: String,
    val user: User
)