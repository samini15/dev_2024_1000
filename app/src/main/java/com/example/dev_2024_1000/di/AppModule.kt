package com.example.dev_2024_1000.di

import com.example.dev_2024_1000.meal.presentation.meal_search.MealSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MealSearchViewModel)
}