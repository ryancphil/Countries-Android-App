package com.example.vexquiz.core.domain

sealed interface Result<out D, out E: VexError> {
    data class Success<out D>(val data: D): Result<D, Nothing>
    data class Error<out E: VexError>(val error: E): Result<Nothing, E>
}

inline fun <T, E: VexError, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when(this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

fun <T, E: VexError> Result<T, E>.asEmptyDataResult(): EmptyDataResult<E> {
    return map { }
}

typealias EmptyDataResult<E> = Result<Unit, E>