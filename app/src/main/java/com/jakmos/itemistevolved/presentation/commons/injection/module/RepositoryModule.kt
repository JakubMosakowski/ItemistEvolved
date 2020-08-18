package com.jakmos.itemistevolved.presentation.commons.injection.module

import com.jakmos.itemistevolved.data.db.ItemistDatabase
import com.jakmos.itemistevolved.domain.model.project.DateTime
import com.jakmos.itemistevolved.domain.usecase.GetChecklistsUseCase
import com.jakmos.itemistevolved.domain.usecase.InsertChecklistUseCase
import com.jakmos.itemistevolved.domain.usecase.RemoveChecklistUseCase
import org.koin.dsl.module

val repositoryModule = module {
    single { ItemistDatabase.getDatabase(get()).checklistDao() }
    single { GetChecklistsUseCase(get()) }
    single { InsertChecklistUseCase(DateTime(), get(), get()) }
    single { RemoveChecklistUseCase(get(), get()) }
}
