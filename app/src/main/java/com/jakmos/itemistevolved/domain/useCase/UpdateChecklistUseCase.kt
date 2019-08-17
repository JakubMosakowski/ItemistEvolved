package com.jakmos.itemistevolved.domain.useCase

import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.entity.ChecklistEntity
import com.jakmos.itemistevolved.domain.model.project.DateTimeInterface

class UpdateChecklistUseCase(
    private val dateTime: DateTimeInterface,
    private val dao: ChecklistDao
) : UseCase<Checklist, Int>() {

    override suspend fun doWork(param: Checklist) =
        dao.updateChecklist(
            ChecklistEntity(
                param.name,
                param.image,
                param.createdAt ?: dateTime.date,
                dateTime.date,
                param.lines,
                param.id
            )
        )
}
