package com.example.clean.data

sealed class ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error(val error: CustomError) : ResultState<Nothing>()
}