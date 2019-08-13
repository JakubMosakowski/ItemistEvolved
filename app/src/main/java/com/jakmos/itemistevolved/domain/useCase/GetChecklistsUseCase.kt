package com.jakmos.itemistevolved.domain.useCase

import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.entity.ChecklistEntity.Companion.entityToModel
import com.jakmos.itemistevolved.domain.model.project.None

class GetChecklistsUseCase(private val dao: ChecklistDao) : UseCase<None, List<Checklist>>() {

    override suspend fun doWork(param: None) = dao.getAll().map { entityToModel(it) }
}