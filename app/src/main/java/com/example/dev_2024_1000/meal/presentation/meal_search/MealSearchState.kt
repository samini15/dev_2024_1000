package com.example.dev_2024_1000.meal.presentation.meal_search

import com.example.dev_2024_1000.meal.presentation.model.MealUI

data class MealSearchState(
    val searchQuery: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,

    val meals: List<MealUI> = emptyList()
)
