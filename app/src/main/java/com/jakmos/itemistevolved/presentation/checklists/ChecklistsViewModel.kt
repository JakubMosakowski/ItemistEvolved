package com.jakmos.itemistevolved.presentation.checklists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.project.None
import com.jakmos.itemistevolved.domain.model.project.State
import com.jakmos.itemistevolved.domain.useCase.GetChecklistsUseCase
import com.jakmos.itemistevolved.presentation.base.BaseViewModel

class ChecklistsViewModel(
    private val getChecklistsUseCase: GetChecklistsUseCase
) : BaseViewModel() {

    private val _state = MutableLiveData<State<List<Checklist>>>().apply {
        this.value = State.Loading()
    }

    val state: LiveData<State<List<Checklist>>> = _state

    init {
        loadData()
    }

    private fun loadData() {
        _state.value = State.Loading()
        getChecklistsUseCase.execute(viewModelScope, None()) {
            it.either(::handleFailure, ::handleSuccessLoad)
        }
    }

    private fun handleSuccessLoad(list: List<Checklist>) {
        when {
            list.isEmpty() -> _state.value = State.Empty()
            else -> _state.value = State.Success(list)
        }
    }

    private fun handleFailure(error: Exception) {
        _state.value = State.Error(error) { loadData() }
    }

    fun onEditClicked(model: Checklist) {
        val directions = ChecklistsFragmentDirections.actionChecklistsFragmentToAddFragment(model)
        navigate(directions)
    }

    fun onItemClicked(model: Checklist) {
        val directions = ChecklistsFragmentDirections.actionChecklistsFragmentToChecklistDetailFragment(model)
        navigate(directions)
    }
}
