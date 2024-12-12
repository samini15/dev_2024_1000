package com.example.dev_2024_1000

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.dev_2024_1000.ui.theme.Dev_2024_1000Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.dev_2024_1000.navigation.NavigationRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var showSplashScreen by mutableStateOf(true)
        splashScreen.setKeepOnScreenCondition { showSplashScreen }
        lifecycleScope.launch {
            delay(2500) // 2.5 seconds
            showSplashScreen = false
            withContext(Dispatchers.Main) {
                splashScreen.setKeepOnScreenCondition { showSplashScreen }
                setContent {
                    Dev_2024_1000Theme {
                        Surface(
                            modifier = Modifier
                                .fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            val navController = rememberNavController()
                            NavigationRoot(navController = navController)
                        }
                    }
                }
            }
        }
    }
}