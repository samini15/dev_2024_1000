package com.example.dev_2024_1000.meal.presentation.meal_detail

sealed interface MealDetailAction {
    data class OnLoadMeal(val idMeal: String): MealDetailAction
    object OnBackClick: MealDetailAction
}