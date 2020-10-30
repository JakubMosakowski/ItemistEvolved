package com.jakmos.itemistevolved.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.jakmos.itemistevolved.domain.manager.ChecklistDomainManager
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.presentation.base.lifecycle.BaseViewModel
import com.jakmos.itemistevolved.sampledata.PresentationMockData
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
  private val checklistManager: ChecklistDomainManager
) : BaseViewModel() {

  //region Observe

  val checklists: LiveData<List<Checklist>> =
    checklistManager.observeChecklists()

  //endregion

  //region Init

  init {
    //TODO it fills database only for testing purposes. Delete that later.

    viewModelScope.launch {
      checklistManager
        .clearChecklists()

      PresentationMockData
        .CHECKLISTS
        .forEach {
          checklistManager
            .addChecklist(it)
        }
    }
  }

  //endregion

  //region Add

  fun onAddClicked() {

  }

  //endregion

  //region Edit

  //endregion

  //region Remove

  //endregion

//    private val toBeRemoved = MutableLiveData<List<Checklist>>()

//    val checklists = MediatorLiveData<List<Checklist>>()

//    init {
//        checklists.addSource(toBeRemoved) {
//            checklists.value = combineLiveData()
//        }
//        checklists.addSource(_checklists) {
//            checklists.value = combineLiveData()
//        }
//    }
//
//    private fun combineLiveData(): List<Checklist>? {
//        return (_checklists.value ?: emptyList()) - (toBeRemoved.value ?: emptyList())
//    }
//
//    fun loadData() {
//        _state.value = State.Loading()
//        getChecklistsUseCase.execute(viewModelScope, None) {
//            it.either(::handleFailureLoad, ::handleSuccessLoad)
//        }
//    }
//
//    private fun handleSuccessLoad(list: List<Checklist>) {
//        when {
//            list.isEmpty() -> _state.value = State.Empty()
//            else -> _state.value = State.Success(None)
//        }
//        _checklists.value = list
//    }
//
//    private fun handleFailureLoad(error: Exception) {
//        _state.value = State.Error(error) { loadData() }
//    }
//
//    fun onEditClicked(model: Checklist) {
//        val directions = ChecklistsFragmentDirections.actionChecklistsFragmentToAddFragment(model)
//        navigate(directions)
//    }
//
//    fun onItemClicked(model: Checklist) {
//        val directions =
//            ChecklistsFragmentDirections.actionChecklistsFragmentToChecklistDetailFragment(model)
//        navigate(directions)
//    }
//
//    fun onDeleteClicked(model: Checklist) {
//        toBeRemoved.value = (toBeRemoved.value ?: emptyList()) + model
//        _state.value = State.Removing()
//    }
//
//    private fun removeChecklists() {
//        removeChecklistUseCase.execute(viewModelScope, toBeRemoved.value ?: emptyList()) {
//            toBeRemoved.value = emptyList()
//            it.either(::handleFailureLoad, ::handleSuccessLoad)
//        }
//    }
//
//    fun snackbarDismissed() {
//        _state.value = State.Success(None)
//        removeChecklists()
//    }
//
//    fun onUndoClicked() {
//        _state.value = State.Success(None)
//        toBeRemoved.value = emptyList()
//    }
}
