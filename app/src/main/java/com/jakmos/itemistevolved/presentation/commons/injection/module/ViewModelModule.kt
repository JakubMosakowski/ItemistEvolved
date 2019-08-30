package com.jakmos.itemistevolved.presentation.commons.injection.module

import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.presentation.add.AddViewModel
import com.jakmos.itemistevolved.presentation.checklists.ChecklistsViewModel
import com.jakmos.itemistevolved.presentation.detail.ChecklistDetailViewModel
import com.jakmos.itemistevolved.presentation.main.MainActivityViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { MainActivityViewModel() }
    single { ChecklistsViewModel(get()) }
    single { (checklist: Checklist?) ->
        AddViewModel(
            checklist ?: Checklist.create(),
            get(),
            get()
        )
    }
    single { (checklist: Checklist) -> ChecklistDetailViewModel(checklist, get()) }
}
