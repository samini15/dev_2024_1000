package com.example.dev_2024_1000.meal.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class DetailedMealDto(
    val idMeal: String,
    val strMeal: String,
    val strInstructions: String? = null,
    val strMealThumb: String,
    val strTags: String? = null,
    val strYoutube: String? = null,
    val ingredientsWithMeasures: List<Pair<String, String>> = emptyList(),
)
