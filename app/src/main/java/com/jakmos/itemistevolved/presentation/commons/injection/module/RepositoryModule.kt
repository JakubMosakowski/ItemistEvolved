package com.jakmos.itemistevolved.presentation.commons.injection.module

import com.jakmos.itemistevolved.data.db.ItemistDatabase
import com.jakmos.itemistevolved.domain.useCase.GetChecklistsUseCase
import com.jakmos.itemistevolved.domain.useCase.InsertChecklistUseCase
import org.koin.dsl.module

val repositoryModule = module {
    single { ItemistDatabase.getDatabase(get()).checklistDao() }
    single { GetChecklistsUseCase(get()) }
    single { InsertChecklistUseCase(get()) }
}