package com.example.core.domain.util

sealed interface DataError : Error {
  enum class Network : DataError {
    REQUEST_TIMEOUT,
    UNAUTHORIZED,
    CONFLICT,
    TOO_MANY_REQUESTS,
    NO_INTERNET_CONNECTION,
    PAYLOAD_TOO_LARGE,
    SERVER_ERROR,
    SERIALIZATION_ERROR,
    UNKNOWN
  }

  enum class Local : DataError {
    DISK_FULL,
    UNKNOWN
  }
}