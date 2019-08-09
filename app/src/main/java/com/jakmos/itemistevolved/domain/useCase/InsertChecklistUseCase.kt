package com.jakmos.itemistevolved.domain.useCase

import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.model.Checklist


class InsertChecklistUseCase(private val dao: ChecklistDao) : LiveDataUseCase<Checklist, Unit>() {

    override suspend fun doWork(param: Checklist) =
        dao.insert(param)
}