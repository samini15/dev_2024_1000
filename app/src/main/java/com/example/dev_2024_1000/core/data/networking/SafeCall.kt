package com.example.dev_2024_1000.core.data.networking

import com.example.dev_2024_1000.core.domain.util.NetworkError
import io.ktor.client.statement.HttpResponse
import com.example.dev_2024_1000.core.domain.util.Result
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch (_: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET_CONNECTION)
    } catch (_: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION)
    } catch (_: Exception) {
        coroutineContext.ensureActive() // To check if the coroutine is still active:  https://youtu.be/VWlwkqmTLHc?si=KGa_GMuFrJx-ghQI
        return Result.Error(NetworkError.UNKNOWN)
    }
    return responseToResult(response)
}