package com.jakmos.itemistevolved.presentation.detail

import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.presentation.base.BaseViewModel
import timber.log.Timber

class ChecklistDetailViewModel(
    private val checklist: Checklist
) : BaseViewModel() {

    init {
        Timber.tag("KUBA").v("INIT $checklist ")

    }
}
