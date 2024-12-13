package com.example.dev_2024_1000.meal.data.mappers

import com.example.dev_2024_1000.meal.data.networking.dto.IngredientDto
import com.example.dev_2024_1000.meal.domain.Ingredient

fun IngredientDto.toIngredient(): Ingredient {
    return Ingredient(
        idIngredient = idIngredient,
        strIngredient = strIngredient
    )
}