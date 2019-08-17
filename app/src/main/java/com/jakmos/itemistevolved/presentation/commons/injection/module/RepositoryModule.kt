package com.jakmos.itemistevolved.presentation.commons.injection.module

import com.jakmos.itemistevolved.data.db.ItemistDatabase
import com.jakmos.itemistevolved.domain.model.project.DateTime
import com.jakmos.itemistevolved.domain.useCase.GetChecklistsUseCase
import com.jakmos.itemistevolved.domain.useCase.InsertChecklistUseCase
import com.jakmos.itemistevolved.domain.useCase.RemoveChecklistUseCase
import com.jakmos.itemistevolved.domain.useCase.UpdateChecklistUseCase
import org.koin.dsl.module

val repositoryModule = module {
    single { ItemistDatabase.getDatabase(get()).checklistDao() }
    single { GetChecklistsUseCase(get()) }
    single { InsertChecklistUseCase(DateTime(), get()) }
    single { UpdateChecklistUseCase(DateTime(), get()) }
    single { RemoveChecklistUseCase(get()) }
}