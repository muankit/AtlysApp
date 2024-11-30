package com.atylsapp.utils

import androidx.annotation.Keep
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

// To expose loading, success and error state to the UI
@Keep
data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {

    companion object {

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String?, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

    }

}

@Keep
enum class Status {
    LOADING, SUCCESS, ERROR
}

inline fun <T> resourceFlow(
    crossinline block: suspend () -> T
): Flow<Resource<T>> {
    return flow { emit(Resource.success(block.invoke())) }
        .onStart { emit(Resource.loading()) }
        .catch { emit(Resource.error(msg = "Something went wrong in API")) } // This can be handled in a better way like throwing proper exception as per http exception
}

inline fun <T> Flow<T>.collectLatest(
    scope: CoroutineScope,
    crossinline action: suspend (value: T) -> Unit
): Job {
    return scope.launch { collectLatest { action(it) } }
}




