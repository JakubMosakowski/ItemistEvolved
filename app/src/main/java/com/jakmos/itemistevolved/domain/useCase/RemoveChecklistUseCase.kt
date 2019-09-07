package com.jakmos.itemistevolved.domain.useCase

import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.project.None

class RemoveChecklistUseCase(
    private val dao: ChecklistDao,
    private val getChecklistsUseCase: GetChecklistsUseCase
) : UseCase<List<Checklist>, List<Checklist>>() {

    override suspend fun doWork(param: List<Checklist>): List<Checklist> {
        param.forEach {
            dao.deleteById(it.id)
        }

        return getChecklistsUseCase.doWork(None)
    }
}
