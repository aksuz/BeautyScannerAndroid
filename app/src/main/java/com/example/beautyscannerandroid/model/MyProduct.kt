package com.example.beautyscannerandroid.model

import java.time.LocalDate

data class MyProduct (
    val id: Long,
    val productId: Product,
    val openingDate: LocalDate?,
    val expirationTime: LocalDate?,
    val productRating: Double?
)
