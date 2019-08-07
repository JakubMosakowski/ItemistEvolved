package com.jakmos.itemistevolved.domain.useCase

import androidx.lifecycle.MutableLiveData
import com.jakmos.itemistevolved.domain.model.ItemistResult
import kotlinx.coroutines.runBlocking
import timber.log.Timber

abstract class LiveDataUseCase<Param, Data> {
    protected abstract suspend fun doWork(param: Param): Data

    fun execute(
        param: Param
    ): ItemistResult<Data> {

        return runBlocking {
            try {
                val liveData = MutableLiveData(doWork(param))

                ItemistResult.Success<Data>(liveData)
            } catch (e: Exception) {
                Timber.tag("KUBA").e("Error occurred: $e")

                ItemistResult.Error<Data>(e)
            }
        }
    }
}