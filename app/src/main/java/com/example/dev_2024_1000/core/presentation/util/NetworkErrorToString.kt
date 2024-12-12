package com.example.dev_2024_1000.core.presentation.util

import android.content.Context
import com.example.dev_2024_1000.core.domain.util.NetworkError
import com.example.dev_2024_1000.R

fun NetworkError.toString(context: Context): String {
    val resId = when (this) {
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        NetworkError.NO_INTERNET_CONNECTION -> R.string.error_no_internet_connection
        NetworkError.SERVER_ERROR -> R.string.error_unknown
        NetworkError.SERIALIZATION -> R.string.error_serialization
        NetworkError.UNKNOWN -> R.string.error_unknown
    }
    return context.getString(resId)
}