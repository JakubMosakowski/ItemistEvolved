package com.jakmos.itemistevolved.domain.useCase

import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.entity.ChecklistEntity
import com.jakmos.itemistevolved.domain.model.project.DateTimeInterface

class InsertChecklistUseCase(
    private val dateTime: DateTimeInterface,
    private val dao: ChecklistDao
) : UseCase<Checklist, Unit>() {

    override suspend fun doWork(param: Checklist) =
        dao.insert(ChecklistEntity(param.id, param.name, param.image, dateTime.date, dateTime.date, param.lines))
}
