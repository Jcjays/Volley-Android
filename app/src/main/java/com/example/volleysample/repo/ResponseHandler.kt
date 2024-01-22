package com.example.volleysample.repo

sealed class ResponseHandler<out T> {
    class Loading<T>() : ResponseHandler<T>()
    data class Success<T>(val response: T) : ResponseHandler<T>()
    data class Failure<T>(val throwable: Throwable, val message: String = "") : ResponseHandler<T>()
}