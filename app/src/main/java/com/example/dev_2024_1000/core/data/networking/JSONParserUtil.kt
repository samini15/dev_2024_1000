package com.example.dev_2024_1000.core.data.networking

import com.example.dev_2024_1000.meal.data.networking.dto.DetailedMealDto
import org.json.JSONObject

fun parseMealWithIngredientsAndMeasures(json: JSONObject): DetailedMealDto {
    val ingredients = mutableListOf<String>()
    val measures = mutableListOf<String>()

    val meals = json.getJSONArray("meals")
    val mealObject = meals.getJSONObject(0)
    for (i in 1..20) {
        val ingredient = mealObject.optString("strIngredient$i", "").trim()
        val measure = mealObject.optString("strMeasure$i", "").trim()

        // Only add non-empty values
        if (ingredient.isNotEmpty() || measure.isNotEmpty()) {
            ingredients.add(ingredient)
            measures.add(measure)
        }
    }

    // Combine ingredients with measures
    val ingredientsWithMeasures = ingredients.zip(measures) { ingredient, measure ->
        ingredient to measure
    }

    return DetailedMealDto(
        idMeal = mealObject.getString("idMeal"),
        strMeal = mealObject.getString("strMeal"),
        strInstructions = mealObject.getString("strInstructions"),
        strMealThumb = mealObject.getString("strMealThumb"),
        strTags = mealObject.optString("strTags", null),
        strYoutube = mealObject.getString("strYoutube"),
        ingredientsWithMeasures = ingredientsWithMeasures,
    )
}