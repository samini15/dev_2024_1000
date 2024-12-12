package com.example.dev_2024_1000.core.data.networking

import com.example.dev_2024_1000.BuildConfig

fun constructUrl(url: String): String {
    return when {
        url.contains(BuildConfig.BASE_URL) -> url
        url.startsWith("/") -> BuildConfig.BASE_URL + url.drop(1) // Drop the slash
        else -> BuildConfig.BASE_URL + url
    }
}