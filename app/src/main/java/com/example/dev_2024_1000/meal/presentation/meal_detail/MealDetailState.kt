package com.example.dev_2024_1000.meal.presentation.meal_detail

import com.example.dev_2024_1000.meal.presentation.model.DetailedMealUi

data class MealDetailState(
    val meal: DetailedMealUi = DetailedMealUi(
        idMeal = "",
        strMeal = "",
        strInstructions = "",
        strMealThumb = "",
        strTags = "",
        strYoutube = "",
        ingredientsWithMeasures = emptyList()
    )
)
