package com.example.beautyscannerandroid.model

import java.time.LocalDate

data class MyProduct (
    val id: Long?,
    val productId: Product,
    val openingDate: String?,
    val expirationTime: String?,
    val productRating: Double?
)
