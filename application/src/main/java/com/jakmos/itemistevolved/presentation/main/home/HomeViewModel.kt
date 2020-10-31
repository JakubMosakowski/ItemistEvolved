package com.jakmos.itemistevolved.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.windly.limbo.mvvm.lifecycle.SingleLiveEvent
import com.jakmos.itemistevolved.domain.manager.ChecklistDomainManager
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.presentation.base.lifecycle.BaseViewModel
import com.jakmos.itemistevolved.utility.livedata.addToList
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
  private val checklistManager: ChecklistDomainManager
) : BaseViewModel() {

  //region Observe

  private val _checklists: LiveData<List<Checklist>> =
    checklistManager.observeChecklists()

  val checklists = MediatorLiveData<List<Checklist>>()

  //endregion

  //region Add

  private val _addClicked: SingleLiveEvent<Any> =
    SingleLiveEvent()

  internal val addClicked: LiveData<Any> =
    _addClicked

  fun onAddClicked() =
    _addClicked.postValue(true)

  //endregion

  //region Delete

  private val _deleteSnackbarRequested: SingleLiveEvent<Any> =
    SingleLiveEvent()

  internal val deleteSnackbarRequested: LiveData<Any> =
    _deleteSnackbarRequested

  private val checklistsToBeRemoved: MutableLiveData<List<Checklist>> =
    MutableLiveData()

  fun onDeleteClicked(checklist: Checklist) {

    // Post new list of values to be removed.
    checklistsToBeRemoved.addToList(checklist)

    // Show snackbar.
    _deleteSnackbarRequested.post()
  }

  private fun subtractRemovedFromChecklists(): List<Checklist> {
    val checklists = _checklists.value.orEmpty()
    val toBeRemoved = checklistsToBeRemoved.value.orEmpty()

    return checklists - toBeRemoved
  }

  fun onSnackbarDismiss() {
    viewModelScope.launch {
      checklistManager
        .removeChecklists(checklistsToBeRemoved.value.orEmpty())
    }
  }

  fun onUndoClicked() {
    checklistsToBeRemoved.postValue(emptyList())
  }

  //endregion

  //region Init

  init {

    // Add sources to checklist mediator live data.

    checklists.addSource(checklistsToBeRemoved) {
      checklists.value = subtractRemovedFromChecklists()
    }

    checklists.addSource(_checklists) {
      checklists.value = subtractRemovedFromChecklists()
    }
  }

  //endregion
}
