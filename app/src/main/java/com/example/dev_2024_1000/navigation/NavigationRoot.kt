package com.example.dev_2024_1000.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dev_2024_1000.meal.presentation.meal_search.MealSearchScreen
import com.example.dev_2024_1000.meal.presentation.meal_search.MealSearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationRoot(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Route.MEAL) {
        mealGraph(navController)
    }
}

private fun NavGraphBuilder.mealGraph(navController: NavHostController) {
    navigation(startDestination = Route.MEAL_SEARCH_SCREEN, route = Route.MEAL) {
        composable(Route.MEAL_SEARCH_SCREEN) {
            val viewModel = koinViewModel<MealSearchViewModel>()
            val state = viewModel.state
            MealSearchScreen(
                state = state,
                onAction = {
                    viewModel.onAction(it)
                }
            )
        }
    }
}