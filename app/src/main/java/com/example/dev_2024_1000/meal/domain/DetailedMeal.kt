package com.example.dev_2024_1000.meal.domain

data class DetailedMeal(
    val idMeal: String,
    val strMeal: String,
    val strInstructions: String? = null,
    val strMealThumb: String,
    val strTags: String? = null,
    val strYoutube: String? = null,
    val ingredientsWithMeasures: List<Pair<String, String>>
)
