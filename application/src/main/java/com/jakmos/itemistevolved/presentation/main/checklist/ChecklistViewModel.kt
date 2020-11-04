package com.jakmos.itemistevolved.presentation.main.checklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.windly.limbo.utility.primitives.orZero
import com.jakmos.itemistevolved.domain.manager.ChecklistDomainManager
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.presentation.base.lifecycle.BaseViewModel
import javax.inject.Inject

class ChecklistViewModel @Inject constructor(
  private val checklistManager: ChecklistDomainManager
) : BaseViewModel() {

  //region Checklist

  private val _checklist: MutableLiveData<Checklist> =
    MutableLiveData()

  val checklist: LiveData<Checklist> =
    _checklist

  fun onChecklistAvailable(checklist: Checklist) {
    this._checklist.postValue(checklist)
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
    val selectedCount = checklist.value?.getNumberOfSelectedSubsection().orZero()
    val max = checklist.value?.subsections?.size.orZero()

    return "($selectedCount/$max)"
  }

  //endregion

//
//    private val _checklist = MutableLiveData(checklist)
//    val checklistLiveData: LiveData<Checklist> = _checklist
//
//    fun clearClicked() {
//        val lines = getClearedLines()
//        _checklist.value = _checklist.value?.copy(lines = lines)
//        saveToDb()
//    }
//
//    private fun saveToDb() {
//        val updatedChecklist = _checklist.value ?: return
//        insertChecklistUseCase.execute(viewModelScope, updatedChecklist) {
//            it.either({}, {})
//        }
//    }
//
//    private fun getClearedLines() =
//        _checklist.value?.lines?.map { it.copy(isChecked = false) } ?: emptyList()
//
//    override fun onItemClicked(model: Item) {
//        _checklist.value = getUpdatedChecklist(model)
//        saveToDb()
//    }
//
//    private fun getUpdatedChecklist(model: Item): Checklist? {
//        var items = _checklist.value?.lines ?: emptyList()
//
//        items = items.map {
//            return@map if (it == model)
//                it.copy(isChecked = !it.isChecked)
//            else
//                it
//        }
//        return _checklist.value?.copy(lines = items)
//    }
}
