package com.jakmos.itemistevolved.domain.model.project

sealed class State<T> {
    class Loading<T> : State<T>()
    class Removing<T> : State<T>()
    class Empty<T> : State<T>()
    data class Success<T>(val data: T) : State<T>()
    data class Error<T>(val error: Exception, val cause: () -> Unit = {}) : State<T>()

    fun isLoading(): Boolean = this is Loading
    fun isRemoving(): Boolean = this is Removing
    fun isEmpty(): Boolean = this is Empty
    fun isSuccess(): Boolean = this is Success
    fun isError(): Boolean = this is Error
}
