package com.jakmos.itemistevolved.presentation.main.add

import com.jakmos.itemistevolved.presentation.base.lifecycle.BaseViewModel
import javax.inject.Inject

class AddViewModel @Inject constructor(): BaseViewModel() {

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
