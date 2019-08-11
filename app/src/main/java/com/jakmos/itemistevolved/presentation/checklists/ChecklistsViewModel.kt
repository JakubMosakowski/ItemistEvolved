package com.jakmos.itemistevolved.presentation.checklists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.project.None
import com.jakmos.itemistevolved.domain.model.project.State
import com.jakmos.itemistevolved.domain.useCase.GetChecklistsUseCase

class ChecklistsViewModel(
    private val getChecklistsUseCase: GetChecklistsUseCase
) : ViewModel() {

    val state = MutableLiveData<State<List<Checklist>>>().apply {
        this.value = State.Loading()
    }

    init {
        loadData()
    }

    private fun loadData() {
        state.value = State.Loading()
        getChecklistsUseCase.execute(viewModelScope, None()) {
            it.either(::handleFailure, ::handleSuccessLoad)
        }
    }

    private fun handleSuccessLoad(list: List<Checklist>) {
        when {
            list.isEmpty() -> state.value = State.Empty()
            else -> state.value = State.Success(list)
        }
    }

    private fun handleFailure(error: Exception) {
        state.value = State.Error(error) { loadData() }
    }
}
