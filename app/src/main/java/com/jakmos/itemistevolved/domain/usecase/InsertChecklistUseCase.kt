package com.jakmos.itemistevolved.domain.usecase

import com.jakmos.itemistevolved.persistence.cache.database.dao.ChecklistDao
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.persistence.cache.database.entity.ChecklistEntity
import com.jakmos.itemistevolved.domain.model.project.DateTimeInterface
import com.jakmos.itemistevolved.domain.model.project.None
import com.jakmos.itemistevolved.domain.model.project.UseCase

class InsertChecklistUseCase(
    private val dateTime: DateTimeInterface,
    private val dao: com.jakmos.itemistevolved.persistence.cache.database.dao.ChecklistDao,
    private val getChecklistsUseCase: GetChecklistsUseCase
) : UseCase<Checklist, List<Checklist>>() {

    override suspend fun doWork(param: Checklist): List<Checklist> {
        dao.insert(createEntity(param))

        return getChecklistsUseCase.doWork(None)
    }

    private fun createEntity(param: Checklist): com.jakmos.itemistevolved.persistence.cache.database.entity.ChecklistEntity =
        com.jakmos.itemistevolved.persistence.cache.database.entity.ChecklistEntity(
            param.id,
            param.name,
            param.image,
            param.createdAt ?: dateTime.date,
            dateTime.date,
            param.lines
        )
}
