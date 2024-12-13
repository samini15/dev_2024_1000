package com.example.dev_2024_1000.meal.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class IngredientsResponseDto(
    val meals: List<IngredientDto>
)
