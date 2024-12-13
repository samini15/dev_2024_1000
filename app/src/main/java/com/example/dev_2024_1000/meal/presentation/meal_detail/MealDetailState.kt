package com.example.dev_2024_1000.meal.presentation.meal_detail

import com.example.dev_2024_1000.meal.presentation.model.MealUI

data class MealDetailState(
    val meal: MealUI = MealUI(
        idMeal = "",
        strMeal = "",
        strMealThumb = "",
        strTags = "",
        strYoutube = ""
    )
)
