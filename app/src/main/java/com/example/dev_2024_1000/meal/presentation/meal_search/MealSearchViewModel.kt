package com.example.dev_2024_1000.meal.presentation.meal_search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MealSearchViewModel: ViewModel() {

    var state by mutableStateOf(MealSearchState())
        private set


    fun onAction(action: MealSearchAction) {
        when (action) {
            is MealSearchAction.OnSearchQueryChanged -> {
                state = state.copy(searchQuery = action.query)
            }

            is MealSearchAction.OnSearchMeal -> {
                executeSearch()
            }
            else -> Unit
        }
    }
    private fun executeSearch() {

    }
}