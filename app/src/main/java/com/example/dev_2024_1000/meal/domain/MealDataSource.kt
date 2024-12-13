package com.example.dev_2024_1000.meal.domain

import com.example.dev_2024_1000.core.domain.util.NetworkError
import com.example.dev_2024_1000.core.domain.util.Result

interface MealDataSource {
    suspend fun searchMeals(searchQuery: String): Result<List<Meal>, NetworkError>
    suspend fun getIngredients(): Result<List<Ingredient>, NetworkError>
    suspend fun filterMealsByIngredient(ingredient: String): Result<List<Meal>, NetworkError>
    suspend fun generateRandomMeal(): Result<List<Meal>, NetworkError>
}