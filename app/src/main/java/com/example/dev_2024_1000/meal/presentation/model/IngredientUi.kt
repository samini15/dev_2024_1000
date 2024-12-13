package com.example.dev_2024_1000.meal.presentation.model

import com.example.dev_2024_1000.BuildConfig
import com.example.dev_2024_1000.meal.domain.Ingredient


data class IngredientUi(
    val idIngredient: String,
    val strIngredient: String
) {
    fun constructIngredientThumbUrl(): String {
        return "${BuildConfig.INGREDIENT_IMAGE_URL}$strIngredient.png"
    }
}

// Mapper
fun Ingredient.toIngredientUi(): IngredientUi {
    return IngredientUi(
        idIngredient = idIngredient,
        strIngredient = strIngredient
    )
}
