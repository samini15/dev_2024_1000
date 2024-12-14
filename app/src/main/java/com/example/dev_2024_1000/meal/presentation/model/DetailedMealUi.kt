package com.example.dev_2024_1000.meal.presentation.model

import com.example.dev_2024_1000.meal.domain.DetailedMeal

data class DetailedMealUi(
    val idMeal: String,
    val strMeal: String,
    val strInstructions: String? = null,
    val strMealThumb: String,
    val strTags: String? = null,
    val strYoutube: String? = null,
    val ingredientsWithMeasures: List<Pair<String, String>>
)

// MAPPER
fun DetailedMeal.toDetailedMealUI(): DetailedMealUi {
    return DetailedMealUi(
        idMeal = idMeal,
        strMeal = strMeal,
        strInstructions = strInstructions,
        strMealThumb = strMealThumb,
        strTags = strTags,
        strYoutube = strYoutube,
        ingredientsWithMeasures = ingredientsWithMeasures
    )
}
