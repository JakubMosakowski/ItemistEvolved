package com.jakmos.itemistevolved.presentation.main.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.windly.limbo.mvvm.lifecycle.SingleLiveEvent
import com.jakmos.itemistevolved.domain.manager.ChecklistDomainManager
import com.jakmos.itemistevolved.presentation.base.lifecycle.BaseViewModel
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
      checklistManager
        .addChecklist(title.value.orEmpty(), emptyList())

      _submitClicked.post()
    }

  //endregion

  //region Title

  val title: MutableLiveData<String> =
    MutableLiveData("")

  //endregion

  //region Items


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
