package com.example.dev_2024_1000.meal.presentation.meal_search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dev_2024_1000.core.domain.util.onSuccess
import com.example.dev_2024_1000.meal.domain.MealDataSource
import com.example.dev_2024_1000.meal.presentation.model.toMealUI
import kotlinx.coroutines.launch

class MealSearchViewModel(
    private val mealDataSource: MealDataSource
): ViewModel() {

    var state by mutableStateOf(MealSearchState())
        private set


    fun onAction(action: MealSearchAction) {
        when (action) {
            is MealSearchAction.OnSearchQueryChanged -> {
                state = state.copy(searchQuery = action.query)
            }

            is MealSearchAction.OnSearchActiveChanged -> {
                state = state.copy(
                    isHintVisible = !action.isActive && state.searchQuery.isBlank(),
                    isSearching = action.isActive
                )
            }

            is MealSearchAction.OnSearchMeal -> {
                executeSearch()
            }
            else -> Unit
        }
    }
    private fun executeSearch() = viewModelScope.launch {
        state = state.copy(
            isSearching = true,
            meals = emptyList()
        )

        mealDataSource
            .searchMeals(state.searchQuery)
            .onSuccess { meals ->
                state = state.copy(meals = meals.map { it.toMealUI() })
            }
            /*.onError {
                state = state.copy(error = it)
            }*/
    }
}