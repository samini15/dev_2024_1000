package com.example.dev_2024_1000.meal.presentation.model

import com.example.dev_2024_1000.meal.domain.Meal

data class MealUI(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)

// MAPPER
fun Meal.toMealUI(): MealUI {
    return MealUI(
        idMeal = idMeal,
        strMeal = strMeal,
        strMealThumb = strMealThumb
    )
}