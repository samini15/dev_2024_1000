package com.example.dev_2024_1000

import android.app.Application
import com.example.dev_2024_1000.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MealApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MealApp)
            modules(appModule)
        }

    }
}