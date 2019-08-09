package com.jakmos.itemistevolved.domain.useCase

import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.None

class GetChecklistsUseCase(private val dao: ChecklistDao) : LiveDataUseCase<None, List<Checklist>>() {

    override suspend fun doWork(param: None) =
        dao.getAll()
}