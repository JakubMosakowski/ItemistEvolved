package com.jakmos.itemistevolved.domain.usecase

import com.jakmos.itemistevolved.persistence.cache.database.dao.ChecklistDao
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.persistence.cache.database.entity.ChecklistEntity.Companion.entityToModel
import com.jakmos.itemistevolved.domain.model.project.None
import com.jakmos.itemistevolved.domain.model.project.UseCase

class GetChecklistsUseCase(
    private val dao: com.jakmos.itemistevolved.persistence.cache.database.dao.ChecklistDao
) : UseCase<None, List<Checklist>>() {

    override suspend fun doWork(
        param: None
    ) =
        dao
            .getAll()
            .map(mapper:mapEntityToDomain)
}
