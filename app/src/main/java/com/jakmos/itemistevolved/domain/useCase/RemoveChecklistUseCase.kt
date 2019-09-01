package com.jakmos.itemistevolved.domain.useCase

import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.project.None

class RemoveChecklistUseCase(
    private val dao: ChecklistDao,
    private val getChecklistsUseCase: GetChecklistsUseCase
) : UseCase<Checklist, List<Checklist>>() {

    override suspend fun doWork(param: Checklist): List<Checklist> {
        dao.deleteById(param.id)

        return getChecklistsUseCase.doWork(None)
    }
}
