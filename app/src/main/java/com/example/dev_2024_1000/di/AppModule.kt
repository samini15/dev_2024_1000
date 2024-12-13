package com.example.dev_2024_1000.di

import com.example.dev_2024_1000.core.data.networking.HttpClientFactory
import com.example.dev_2024_1000.meal.data.networking.RemoteMealDataSource
import com.example.dev_2024_1000.meal.domain.MealDataSource
import com.example.dev_2024_1000.meal.presentation.meal_detail.MealDetailViewModel
import com.example.dev_2024_1000.meal.presentation.meal_search.MealSearchViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }

    singleOf(::RemoteMealDataSource).bind<MealDataSource>()
    viewModelOf(::MealSearchViewModel)
    viewModelOf(::MealDetailViewModel)
}