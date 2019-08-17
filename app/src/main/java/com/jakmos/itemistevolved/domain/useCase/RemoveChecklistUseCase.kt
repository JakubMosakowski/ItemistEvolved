package com.jakmos.itemistevolved.domain.useCase

import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.model.Checklist

class RemoveChecklistUseCase(
    private val dao: ChecklistDao
) : UseCase<Checklist, Int>() {

    override suspend fun doWork(param: Checklist) =
        dao.deleteById(param.id)
}
