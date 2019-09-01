package com.jakmos.itemistevolved.domain.useCase

import com.jakmos.itemistevolved.domain.model.project.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class UseCase<Param, Data> {
    abstract suspend fun doWork(param: Param): Data

    fun execute(
        scope: CoroutineScope,
        param: Param,
        onResult: (Either<Exception, Data>) -> Unit = {}

    ) = scope.launch {
        try {
            onResult(Either.Right(doWork(param)))
        } catch (e: Exception) {
            Timber.tag("KUBA").e("Error occurred: $e")
            onResult(Either.Left(e))
        }
    }
}
