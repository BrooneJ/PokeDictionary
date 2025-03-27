package com.example.core.domain.util

sealed interface Result<out D, out E : Error> {
  data class Success<out D>(val data: D) : Result<D, Nothing>
  data class Error<out E : com.example.core.domain.util.Error>(val error: E) : Result<Nothing, E>
}

inline fun <T, D, E : Error> Result<D, E>.map(transform: (D) -> T): Result<T, E> {
  return when (this) {
    is Result.Success -> Result.Success(transform(data))
    is Result.Error -> Result.Error(error)
  }
}

typealias EmptyResult<E> = Result<Unit, E>

fun <T, E : Error> Result<T, E>.asEmptyDataResult(): EmptyResult<E> {
  return map {}
}
