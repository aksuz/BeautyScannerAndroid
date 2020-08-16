package com.example.beautyscannerandroid.model

data class Product(
    val id: Long,
    val barcode: String,
    val name: String,
    val producer: Producer,
    val description: String,
    val category: Category,
    val ingredients: List<Ingredient>?,
    val url: String?,
    val picture: String?,
    val noRatingVotes: Double,
    val sumRainingVotes: Double
)

//todo sprawdzic modele, jesli null to ?