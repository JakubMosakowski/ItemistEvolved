package com.jakmos.itemistevolved.domain.usecase

import com.jakmos.itemistevolved.persistence.cache.database.dao.ChecklistDao
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.project.UseCase

class RemoveChecklistUseCase(
    private val dao: com.jakmos.itemistevolved.persistence.cache.database.dao.ChecklistDao
) : UseCase<List<Checklist>, Unit>() {

    override suspend fun doWork(param: List<Checklist>){
        param.forEach {
            dao.deleteById(it.id)
        }
    }
}
