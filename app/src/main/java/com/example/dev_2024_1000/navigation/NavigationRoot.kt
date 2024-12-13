package com.example.dev_2024_1000.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.dev_2024_1000.meal.presentation.meal_detail.MealDetailAction
import com.example.dev_2024_1000.meal.presentation.meal_detail.MealDetailScreen
import com.example.dev_2024_1000.meal.presentation.meal_detail.MealDetailViewModel
import com.example.dev_2024_1000.meal.presentation.meal_search.MealSearchAction
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
                onAction = { action ->
                    if (action is MealSearchAction.OnMealClick) {
                        navController.navigate(route = Route.MEAL_DETAIL_SCREEN + "/${action.idMeal}")
                    }
                    viewModel.onAction(action)
                }
            )
        }

        composable(
            route = Route.MEAL_DETAIL_SCREEN + "/{idMeal}",
            arguments = listOf(
                navArgument("idMeal") {
                    type = NavType.StringType
                }
            )
        ) {
            it.arguments?.apply {
                val idMeal = getString("idMeal")!!

                val viewModel = koinViewModel<MealDetailViewModel>()
                val state = viewModel.state
                MealDetailScreen(
                    idMeal = idMeal,
                    state = state,
                    onAction = { action ->
                        when (action) {
                            is MealDetailAction.OnBackClick -> {
                                navController.navigateUp()
                            }
                            else -> Unit
                        }
                        viewModel.onAction(action)
                    }
                )

            }
        }
    }
}