package com.jakmos.itemistevolved.presentation.checklists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.project.None
import com.jakmos.itemistevolved.domain.model.project.State
import com.jakmos.itemistevolved.domain.useCase.GetChecklistsUseCase
import com.jakmos.itemistevolved.domain.useCase.RemoveChecklistUseCase
import com.jakmos.itemistevolved.presentation.base.BaseViewModel

class ChecklistsViewModel(
    private val getChecklistsUseCase: GetChecklistsUseCase,
    private val removeChecklistUseCase: RemoveChecklistUseCase
) : BaseViewModel() {

    private val _state = MutableLiveData<State<None>>().apply {
        this.value = State.Loading()
    }
    private val _checklists = MutableLiveData<List<Checklist>>()
    private val toBeRemoved = MutableLiveData<List<Checklist>>()

    val state: LiveData<State<None>> = _state
    val checklists = MediatorLiveData<List<Checklist>>()

    init {
        checklists.addSource(toBeRemoved) {
            checklists.value = combineLiveData()
        }
        checklists.addSource(_checklists) {
            checklists.value = combineLiveData()
        }
    }

    private fun combineLiveData(): List<Checklist>? {
        return (_checklists.value ?: emptyList()) - (toBeRemoved.value ?: emptyList())
    }

    fun loadData() {
        _state.value = State.Loading()
        getChecklistsUseCase.execute(viewModelScope, None) {
            it.either(::handleFailureLoad, ::handleSuccessLoad)
        }
    }

    private fun handleSuccessLoad(list: List<Checklist>) {
        when {
            list.isEmpty() -> _state.value = State.Empty()
            else -> {
                _state.value = State.Success(None)
                _checklists.value = list
            }
        }
    }

    private fun handleFailureLoad(error: Exception) {
        _state.value = State.Error(error) { loadData() }
    }

    fun onEditClicked(model: Checklist) {
        val directions = ChecklistsFragmentDirections.actionChecklistsFragmentToAddFragment(model)
        navigate(directions)
    }

    fun onItemClicked(model: Checklist) {
        val directions =
            ChecklistsFragmentDirections.actionChecklistsFragmentToChecklistDetailFragment(model)
        navigate(directions)
    }

    fun onDeleteClicked(model: Checklist) {
        toBeRemoved.value = (toBeRemoved.value ?: emptyList()) + model
        _state.value = State.Removing()
    }

    private fun removeChecklists() {
        _state.value = State.Loading()
        removeChecklistUseCase.execute(viewModelScope, toBeRemoved.value ?: emptyList()) {
            it.either(::handleFailureLoad, ::handleSuccessLoad)
        }
    }

    fun snackbarDismissed() {
        _state.value = State.Success(None)
        removeChecklists()
        toBeRemoved.value = emptyList()
    }

    fun onUndoClicked() {
        _state.value = State.Success(None)
        toBeRemoved.value = emptyList()
    }
}
