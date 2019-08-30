package com.jakmos.itemistevolved.presentation.commons.injection.module

import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.presentation.add.AddViewModel
import com.jakmos.itemistevolved.presentation.checklists.ChecklistsViewModel
import com.jakmos.itemistevolved.presentation.detail.ChecklistDetailViewModel
import com.jakmos.itemistevolved.presentation.main.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainActivityViewModel() }
    viewModel { ChecklistsViewModel(get()) }
    viewModel { (checklist: Checklist?) ->
        AddViewModel(
            checklist ?: Checklist.create(),
            get()
        )
    }
    viewModel { (checklist: Checklist) -> ChecklistDetailViewModel(checklist, get()) }
}
