package com.example.dev_2024_1000.meal.data.mappers

import com.example.dev_2024_1000.meal.data.networking.dto.MealDto
import com.example.dev_2024_1000.meal.domain.Meal

fun MealDto.toMeal(): Meal {
    return Meal(
        idMeal = idMeal,
        strMeal = strMeal,
        strMealThumb = strMealThumb
    )
}