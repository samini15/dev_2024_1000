package com.example.dev_2024_1000.meal.data.networking

import com.example.dev_2024_1000.core.data.networking.constructUrl
import com.example.dev_2024_1000.core.data.networking.safeCall
import com.example.dev_2024_1000.core.domain.util.NetworkError
import com.example.dev_2024_1000.core.domain.util.Result
import com.example.dev_2024_1000.core.domain.util.map
import com.example.dev_2024_1000.meal.data.mappers.toMeal
import com.example.dev_2024_1000.meal.data.networking.dto.MealsResponseDto
import com.example.dev_2024_1000.meal.domain.Meal
import com.example.dev_2024_1000.meal.domain.MealDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteMealDataSource(
    private val httpClient: HttpClient
): MealDataSource {

    override suspend fun searchMeals(searchQuery: String): Result<List<Meal>, NetworkError> {
        return safeCall<MealsResponseDto> {
            httpClient.get(urlString = constructUrl("search.php?s=$searchQuery"))
        }.map { response ->
            response.meals.map { it.toMeal() }
        }
    }
}