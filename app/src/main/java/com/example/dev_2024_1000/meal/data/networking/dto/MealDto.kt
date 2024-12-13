package com.example.dev_2024_1000.meal.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealDto(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strTags: String?,
    val strYoutube: String?
)
