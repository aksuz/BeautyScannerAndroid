package com.example.beautyscannerandroid.model

data class Ingredient(
    val id: Long,
    val name: String,
    val url: String?,
    val allergen: Boolean,
    val irritant: Boolean,
    val natural: Boolean,
    val forbiddenDuringPregnancy: Boolean,
    val comedogenic: Boolean,
    val carcinogenic: Boolean
)
