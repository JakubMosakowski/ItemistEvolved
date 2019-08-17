package com.jakmos.itemistevolved.presentation.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.project.None
import com.jakmos.itemistevolved.domain.model.project.State
import com.jakmos.itemistevolved.domain.useCase.InsertChecklistUseCase
import com.jakmos.itemistevolved.domain.useCase.UpdateChecklistUseCase
import com.jakmos.itemistevolved.presentation.base.BaseViewModel
import timber.log.Timber

class AddViewModel(
    private val checklist: Checklist?,
    private val insertChecklistUseCase: InsertChecklistUseCase,
    private val updateChecklistUseCase: UpdateChecklistUseCase
) : BaseViewModel() {

    private val _state = MutableLiveData<State<None>>().apply {
        this.value = State.Loading()
    }

    val state: LiveData<State<None>> = _state

    init {

    }

    private fun addChecklist(checklist: Checklist) {
        insertChecklistUseCase.execute(viewModelScope, checklist) {
            it.either(::handleFailure, ::handleSuccessLoad)
        }
    }

    private fun handleSuccessLoad(rowsAffectedCount: Long) {
        Timber.tag("KUBA").v("handleSuccessLoad $rowsAffectedCount")
    }

    private fun handleFailure(error: Exception) {
        _state.value = State.Error(error)
    }
}
