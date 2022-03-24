package com.example.issyu39.retrofitcoroutines.ui.model

sealed class State<T> {
    class Loading<T> : State<T>()
    data class Success<T>(val data: T) : State<T>()
    data class Failure<T>(val e: Throwable) : State<T>()
}