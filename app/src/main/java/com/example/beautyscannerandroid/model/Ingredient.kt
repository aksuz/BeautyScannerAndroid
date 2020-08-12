package com.example.beautyscannerandroid.model

data class Ingredient (
    val id: Long,
    val name: String,
    val url: String,
    val isAllergen: Boolean,
    val isIrritant: Boolean,
    val isNatural: Boolean,
    val isForbiddenDuringPregnancy: Boolean,
    val isComedogenic: Boolean,
    val isCarcinogenic: Boolean
)
