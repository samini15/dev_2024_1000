package com.example.dev_2024_1000.meal.presentation.meal_search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dev_2024_1000.core.domain.util.onSuccess
import com.example.dev_2024_1000.meal.domain.MealDataSource
import com.example.dev_2024_1000.meal.presentation.model.toIngredientUi
import com.example.dev_2024_1000.meal.presentation.model.toMealUI
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MealSearchViewModel(
    private val mealDataSource: MealDataSource
): ViewModel() {

    var state by mutableStateOf(MealSearchState())
        private set

    private var mealGeneratorJob: Job? = null


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
                stopGeneratingRandomMeals()
            }

            // Filter by ingredient
            is MealSearchAction.OnFilterByIngredientClick -> {
                loadIngredients()
            }

            is MealSearchAction.OnIngredientSelected -> {
                filterMealsByIngredient(action.ingredient)
                stopGeneratingRandomMeals()
            }

            is MealSearchAction.OnRandomMealClick -> {
                state = state.copy(shouldStopGeneratingMeals = false)
                generateRandomMeal()
            }

            is MealSearchAction.OnMealClick -> Unit

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

    private fun loadIngredients() = viewModelScope.launch {
        mealDataSource
            .getIngredients()
            .onSuccess { ingredients ->
                state = state.copy(ingredients = ingredients.map { it.toIngredientUi() })
            }
    }

    private fun filterMealsByIngredient(ingredient: String) = viewModelScope.launch {
        mealDataSource
            .filterMealsByIngredient(ingredient)
            .onSuccess {
                state = state.copy(meals = it.map { it.toMealUI() })
            }
    }

    private fun generateRandomMeal() {
        if (mealGeneratorJob?.isActive == true) return

        mealGeneratorJob = viewModelScope.launch {
            while (isActive && !state.shouldStopGeneratingMeals) {
                mealDataSource
                    .generateRandomMeal()
                    .onSuccess {
                        state = state.copy(meals = it.map { it.toMealUI() })
                    }
                delay(5000)
            }
        }
    }

    private fun stopGeneratingRandomMeals() {
        state = state.copy(shouldStopGeneratingMeals = true)
        mealGeneratorJob?.cancel()
    }
}