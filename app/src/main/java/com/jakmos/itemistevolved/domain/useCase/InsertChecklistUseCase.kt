package com.jakmos.itemistevolved.domain.useCase

import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.entity.ChecklistEntity
import java.util.*

class InsertChecklistUseCase(private val dao: ChecklistDao) : UseCase<Checklist, Unit>() {

    override suspend fun doWork(param: Checklist) =
        dao.insert(ChecklistEntity(param.id, param.name, param.image, Date(), Date(), param.lines))
}
