package com.jakmos.itemistevolved.domain.usecase

import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.data.entity.ChecklistEntity.Companion.entityToModel
import com.jakmos.itemistevolved.domain.model.project.None
import com.jakmos.itemistevolved.domain.model.project.UseCase

class GetChecklistsUseCase(
    private val dao: ChecklistDao
) : UseCase<None, List<Checklist>>() {

    override suspend fun doWork(
        param: None
    ) =
        dao
            .getAll()
            .map(mapper:mapEntityToDomain)
}
