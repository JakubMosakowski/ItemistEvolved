package com.jakmos.itemistevolved.presentation.main.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.windly.limbo.mvvm.lifecycle.SingleLiveEvent
import co.windly.limbo.utility.primitives.EMPTY
import co.windly.limbo.utility.primitives.orZero
import com.jakmos.itemistevolved.domain.manager.ChecklistDomainManager
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.Subsection
import com.jakmos.itemistevolved.presentation.base.lifecycle.BaseViewModel
import com.jakmos.itemistevolved.utility.livedata.addToList
import com.jakmos.itemistevolved.utility.livedata.removeFromList
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddViewModel @Inject constructor(
  private val checklistManager: ChecklistDomainManager
) : BaseViewModel() {

  //region Checklist

  private var checklist: Checklist? =
    null

  fun onChecklistAvailable(checklist: Checklist?) {
    this.checklist = checklist

    if (checklist == null) return

    // If checklist is not null, we are editing instead of creating new.
    initializeEdit(checklist)
  }

  private fun initializeEdit(checklist: Checklist) {

    // Update initial values.
    titleText.postValue(checklist.name)
    _subsections.postValue(checklist.subsections)
  }

  //endregion

  //region Submit

  private val _submitCompleted: SingleLiveEvent<Any> =
    SingleLiveEvent()

  internal val submitCompleted: LiveData<Any> =
    _submitCompleted

  fun onSubmitClicked() =
    /**
     * Fetch current order of subsections. User could reorder them.
     */
    _currentSubsectionsOrderRequested.post()

  //endregion

  //region Title

  val titleText: MutableLiveData<String> =
    MutableLiveData(String.EMPTY)

  //endregion

  //region Add

  val subsectionText: MutableLiveData<String> =
    MutableLiveData(String.EMPTY)

  private fun getNewSubsection(text: String): Subsection {

    // Get all previous subsections.
    val previousSubsections = _subsections.value.orEmpty()

    // Generate id for new subsection.
    val newId = previousSubsections.maxBy { it.id }?.id.orZero() + 1

    return Subsection(newId, text)
  }

  fun onAddClicked() {

    val text = subsectionText.value.orEmpty()

    // Do not add empty sections.
    if (text.isBlank()) return

    // Add new subsection.
    _subsections.addToList(getNewSubsection(text))

    // Clear subsection edit text.
    subsectionText.postValue(String.EMPTY)
  }

  //endregion

  //region Items

  private val _subsections: MutableLiveData<List<Subsection>> =
    MutableLiveData()

  val subsections: LiveData<List<Subsection>> =
    _subsections

  private val _currentSubsectionsOrderRequested: SingleLiveEvent<Any> =
    SingleLiveEvent()

  internal val currentSubsectionsOrderRequested: LiveData<Any> =
    _currentSubsectionsOrderRequested

  fun postNewSubsectionOrder(subsections: List<Subsection>) =
    viewModelScope.launch {
      checklistManager
        .addChecklist(titleText.value.orEmpty(), subsections, checklist?.id)

      _submitCompleted.post()
    }

  //endregion

  //region Delete

  fun onDeleteClicked(subsection: Subsection) =
    _subsections.removeFromList(subsection)

  //endregion
}
