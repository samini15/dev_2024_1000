package com.example.dev_2024_1000.meal.data.mappers

import com.example.dev_2024_1000.meal.data.networking.dto.DetailedMealDto
import com.example.dev_2024_1000.meal.data.networking.dto.MealDto
import com.example.dev_2024_1000.meal.domain.DetailedMeal
import com.example.dev_2024_1000.meal.domain.Meal

fun MealDto.toMeal(): Meal {
    return Meal(
        idMeal = idMeal,
        strMeal = strMeal,
        strMealThumb = strMealThumb
    )
}

fun DetailedMealDto.toDetailedMeal(): DetailedMeal {
    return DetailedMeal(
        idMeal = idMeal,
        strMeal = strMeal,
        strInstructions = strInstructions,
        strMealThumb = strMealThumb,
        strTags = strTags,
        strYoutube = strYoutube,
        ingredientsWithMeasures = ingredientsWithMeasures,
    )
}