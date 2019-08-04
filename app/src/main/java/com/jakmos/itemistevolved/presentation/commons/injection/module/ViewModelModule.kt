package com.jakmos.itemistevolved.presentation.commons.injection.module

import com.jakmos.itemistevolved.presentation.add.AddViewModel
import com.jakmos.itemistevolved.presentation.checklists.ChecklistsViewModel
import com.jakmos.itemistevolved.presentation.main.MainActivityViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { MainActivityViewModel() }
    single { ChecklistsViewModel() }
    single { AddViewModel() }
}
