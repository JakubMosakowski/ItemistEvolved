package com.jakmos.itemistevolved.presentation.main.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.windly.limbo.mvvm.lifecycle.SingleLiveEvent
import co.windly.limbo.utility.primitives.EMPTY
import co.windly.limbo.utility.primitives.orZero
import com.jakmos.itemistevolved.domain.manager.ChecklistDomainManager
import com.jakmos.itemistevolved.presentation.base.lifecycle.BaseViewModel
import com.jakmos.itemistevolved.presentation.main.add.item.SimpleSubsection
import com.jakmos.itemistevolved.utility.livedata.addToList
import com.jakmos.itemistevolved.utility.livedata.removeFromList
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddViewModel @Inject constructor(
  private val checklistManager: ChecklistDomainManager
) : BaseViewModel() {

  //region Submit

  private val _submitClicked: SingleLiveEvent<Any> =
    SingleLiveEvent()

  internal val submitClicked: LiveData<Any> =
    _submitClicked

  fun onSubmitClicked() =
    viewModelScope.launch {
      val subsectionsTexts = subsections.value.orEmpty().map { it.text }

      checklistManager
        .addChecklist(titleText.value.orEmpty(), subsectionsTexts)

      _submitClicked.post()
    }

  //endregion

  //region Title

  val titleText: MutableLiveData<String> =
    MutableLiveData(String.EMPTY)

  //endregion

  //region Add

  val subsectionText: MutableLiveData<String> =
    MutableLiveData(String.EMPTY)

  private fun getNewSubsection(text: String): SimpleSubsection {

    // Get all previous subsections.
    val previousSubsections = _subsections.value.orEmpty()

    // Generate id for new subsection.
    val newId = previousSubsections.maxBy { it.id }?.id.orZero() + 1

    return SimpleSubsection(newId, text)
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

  private val _subsections: MutableLiveData<List<SimpleSubsection>> =
    MutableLiveData()

  val subsections: LiveData<List<SimpleSubsection>> =
    _subsections

  //endregion

  //region Delete

  fun onDeleteClicked(subsection: SimpleSubsection) =
    _subsections.removeFromList(subsection)

  //endregion

//    private val _state = MutableLiveData<State<None>>()
//    private val _items = MutableLiveData(checklist.lines)
//
//    val state: LiveData<State<None>> = _state
//    val items: LiveData<List<Item>> = _items
//    val lineItemText = MutableLiveData("")
//    val titleText = MutableLiveData(checklist.name)
//    var draggedFromTo = Pair(-1, -1)
//
//    init {
//        showKeyboard()
//    }
//
//    fun addItemClicked() {
//        val text = lineItemText.value
//        if (text.isNullOrEmpty()) return
//
//        val newItems = listOf(Item.create(text)) + (_items.value ?: emptyList())
//        _items.postValue(newItems)
//        lineItemText.postValue("")
//    }
//
//    fun submitClicked() {
//        hideKeyboard()
//        val updatedChecklist = updateInitialChecklist()
//
//        addChecklist(updatedChecklist)
//    }
//
//    private fun updateInitialChecklist() =
//        checklist.copy(
//            name = titleText.value ?: checklist.name,
//            lines = _items.value ?: emptyList()
//        )
//
//
//    private fun addChecklist(checklist: Checklist) {
//        _state.postValue(State.Loading())
//        insertChecklistUseCase.execute(viewModelScope, checklist) {
//            it.either(::handleFailure) { handleSuccess() }
//        }
//    }
//
//    private fun handleSuccess() {
//        _state.value = State.Success(None)
//        val directions = AddFragmentDirections.actionAddFragmentToChecklistsFragment()
//        navigate(directions)
//    }
//
//    private fun handleFailure(error: Exception) {
//        _state.value = State.Error(error)
//    }
//
//    fun onDeleteClicked(model: Item) {
//        val newItems = (_items.value ?: emptyList()) - model
//        _items.postValue(newItems)
//    }
//
//    override fun moveItem(from: Int, to: Int) {
//        val newItems = (_items.value ?: emptyList()).toMutableList()
//        val fromItem = newItems[from]
//
//        newItems.removeAt(from)
//        if (to < from) {
//            newItems.add(to, fromItem)
//        } else {
//            newItems.add(to - 1, fromItem)
//        }
//
//        draggedFromTo = Pair(from, to)
//        _items.postValue(newItems)
//    }
//
//    companion object {
//        val IS_NOT_DRAGGING = Pair(-1, -1)
//    }
}
