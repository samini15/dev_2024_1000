package com.example.dev_2024_1000.meal.presentation.meal_search

sealed interface MealSearchAction {
    object OnSearchMeal : MealSearchAction
    data class OnSearchQueryChanged(val query: String) : MealSearchAction
    data class OnSearchActiveChanged(val isActive: Boolean): MealSearchAction

    object OnFilterByIngredientClick: MealSearchAction
    data class OnIngredientSelected(val ingredient: String): MealSearchAction

    object OnRandomMealClick: MealSearchAction

    data class OnMealClick(val idMeal: String): MealSearchAction
}