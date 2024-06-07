package com.cyrilpillai.marvelverse.core.network.adapter.model

sealed class NetworkResult<T : Any> {
    data class Success<T : Any>(val data: T) : NetworkResult<T>()
    data class Error<T : Any>(val code: Int, val message: String?) : NetworkResult<T>()
    data class Exception<T : Any>(val throwable: Throwable) : NetworkResult<T>()
}