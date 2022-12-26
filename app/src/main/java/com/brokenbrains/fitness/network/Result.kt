package com.brokenbrains.fitness.network


sealed class Result<out T> {
    data class Success<out T>(val data: T? = null): Result<T>()
    data class Loading(val nothing: Nothing? = null): Result<Nothing>()
    data class Failed(val status: String? = null, val message: String? = null): Result<Nothing>()
    data class Exception(val exception: kotlin.Exception? = null): Result<Nothing>()
}
