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
import com.jakmos.itemistevolved.utility.log.ILogger
import com.jakmos.itemistevolved.utility.vocabulary.INVALID_ID
import com.jakmos.itemistevolved.utility.vocabulary.Id
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddViewModel @Inject constructor(
  private val checklistManager: ChecklistDomainManager
) : BaseViewModel() {

  //region Checklist

  private var checklistId: Id = Id.INVALID_ID

  fun onChecklistAvailable(id: Id) = viewModelScope.launch {

    checklistId = id

    // Try to fetch checklist from db.
    try {
      val checklist = checklistManager.getChecklist(checklistId)

      initializeEdit(checklist)
    } catch (e: IllegalArgumentException) {
      ILogger.e("Checklist with id: ($checklistId) was not found.")
    }
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

  fun onSubmitClicked() = viewModelScope.launch {
    checklistManager
      .addChecklist(titleText.value.orEmpty(), subsections.value.orEmpty(), checklistId)

    _submitCompleted.post()
  }

  //endregion

  //region Title

  val titleText: MutableLiveData<String> =
    MutableLiveData(String.EMPTY)

  //endregion

  //region Add

  val subsectionText: MutableLiveData<String> =
    MutableLiveData(String.EMPTY)

  private fun createNewSubsection(text: String): Subsection {

    // Get all previous subsections.
    val previousSubsections = _subsections.value.orEmpty()

    // Generate id for new subsection.
    val newId = previousSubsections.maxByOrNull { it.id }?.id.orZero() + 1

    return Subsection(newId, text)
  }

  fun onAddClicked() {

    val text = subsectionText.value.orEmpty()

    // Do not add empty sections.
    if (text.isBlank()) return

    // Add new subsection.
    _subsections.addToList(createNewSubsection(text))

    // Clear subsection edit text.
    subsectionText.postValue(String.EMPTY)
  }

  //endregion

  //region Items

  private val _subsections: MutableLiveData<List<Subsection>> =
    MutableLiveData()

  val subsections: LiveData<List<Subsection>> =
    _subsections

  fun onSubsectionsReordered(subsections: List<Subsection>) =
    _subsections.postValue(subsections)

  //endregion

  //region Delete

  fun onDeleteClicked(subsection: Subsection) =
    _subsections.removeFromList(subsection)

  //endregion
}
