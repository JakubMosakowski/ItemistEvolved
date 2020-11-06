package com.jakmos.itemistevolved.presentation.main.checklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.windly.limbo.utility.primitives.orZero
import com.jakmos.itemistevolved.domain.manager.ChecklistDomainManager
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.Subsection
import com.jakmos.itemistevolved.presentation.base.lifecycle.BaseViewModel
import com.jakmos.itemistevolved.utility.vocabulary.Id
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChecklistViewModel @Inject constructor(
  private val checklistManager: ChecklistDomainManager
) : BaseViewModel() {

  //region Checklist

  private val _checklist: MutableLiveData<Checklist> =
    MutableLiveData()

  val checklist: LiveData<Checklist> =
    _checklist

  @ExperimentalCoroutinesApi
  fun onChecklistAvailable(checklistId: Id) = viewModelScope.launch {

    checklistManager.observeChecklist(checklistId).collect {
      _checklist.postValue(it)
    }
  }

  //endregion

  //region Toolbar

  private val _title: MutableLiveData<String> =
    MutableLiveData()

  val title: LiveData<String> =
    _title

  fun onChecklistUpdated() {
    this._title.postValue(getTitle())
  }

  private fun getTitle(): String =
    "${getCounterText()} ${checklist.value?.name.orEmpty()}"

  private fun getCounterText(): String {
    val selectedCount = checklist.value?.getNumberOfSelectedSubsections().orZero()
    val max = checklist.value?.subsections?.size.orZero()

    return "($selectedCount/$max)"
  }

  //endregion

  //region Clear

  private fun updateChecklist(): Checklist? =
    checklist.value.apply {
      this?.deselectAllSubsections()
    }

  fun onClearClicked() = viewModelScope.launch {
    val checklist = updateChecklist()

    checklist?.let {
      checklistManager.updateSubsections(it)
    }
  }

  //endregion

  //region Select

  private fun updateChecklist(subsection: Subsection): Checklist? =
    checklist.value.apply {
      this?.selectSubsection(subsection)
    }

  fun onSubsectionClicked(subsection: Subsection) {
    viewModelScope.launch {
      val checklist = updateChecklist(subsection)

      checklist?.let {
        checklistManager.updateSubsections(it)
      }
    }
  }

  //endregion
}
