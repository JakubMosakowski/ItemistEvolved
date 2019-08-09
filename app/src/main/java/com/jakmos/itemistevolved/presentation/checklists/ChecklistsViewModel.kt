package com.jakmos.itemistevolved.presentation.checklists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.None
import com.jakmos.itemistevolved.domain.useCase.GetChecklistsUseCase

class ChecklistsViewModel(
    private val getChecklistsUseCase: GetChecklistsUseCase
) : ViewModel() {

    sealed class ChecklistsState {
        object Loading : ChecklistsState()
        object Empty : ChecklistsState()
        data class Success(val checklists: List<Checklist>) : ChecklistsState()
        data class Error(val error: Exception) : ChecklistsState()
    }

    val state = MutableLiveData<ChecklistsState>().apply {
        this.value = ChecklistsState.Loading
    }

    fun loadData() {
        getChecklistsUseCase.execute(viewModelScope, None()) {
            it.either(::handleFailure, ::handleSuccess)
        }
    }

    private fun handleSuccess(list: List<Checklist>) {
        when {
            list.isEmpty() -> state.value = ChecklistsState.Empty
            list.isNotEmpty() -> state.value = ChecklistsState.Success(list)
        }
    }

    private fun handleFailure(error: Exception) {
        state.value = ChecklistsState.Error(error)
    }
}
