package com.example.dev_2024_1000.meal.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealsResponseDto(
    val meals: List<MealDto>
)
