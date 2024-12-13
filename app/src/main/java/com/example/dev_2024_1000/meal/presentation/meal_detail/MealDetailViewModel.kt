package com.example.dev_2024_1000.meal.presentation.meal_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dev_2024_1000.core.domain.util.onSuccess
import com.example.dev_2024_1000.meal.domain.MealDataSource
import com.example.dev_2024_1000.meal.presentation.model.toMealUI
import kotlinx.coroutines.launch

class MealDetailViewModel(
    private val mealDataSource: MealDataSource
): ViewModel() {
    var state by mutableStateOf(MealDetailState())
        private set

    fun onAction(action: MealDetailAction) {
        when (action) {
            is MealDetailAction.OnLoadMeal -> {
                loadMeal(action.idMeal)
            }

            else -> Unit
        }
    }

    private fun loadMeal(idMeal: String) {
        viewModelScope.launch {
            mealDataSource
                .getMealById(idMeal)
                .onSuccess {
                    state = state.copy(meal = it.map { it.toMealUI() }.first())
                }
        }
    }
}