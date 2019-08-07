package com.jakmos.itemistevolved.domain.model

import androidx.lifecycle.MutableLiveData

sealed class ItemistResult<T> {
    data class Success<T>(val data: MutableLiveData<T>) : ItemistResult<T>()
    data class Error<T>(val error: Exception) : ItemistResult<T>()
}