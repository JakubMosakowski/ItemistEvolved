package com.jakmos.itemistevolved.presentation.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.Item
import com.jakmos.itemistevolved.domain.model.project.None
import com.jakmos.itemistevolved.domain.model.project.State
import com.jakmos.itemistevolved.domain.useCase.InsertChecklistUseCase
import com.jakmos.itemistevolved.domain.useCase.UpdateChecklistUseCase
import com.jakmos.itemistevolved.presentation.add.adapter.ItemAdapter
import com.jakmos.itemistevolved.presentation.base.BaseViewModel
import com.jakmos.itemistevolved.presentation.commons.callback.DragAndDropListener
import timber.log.Timber

class AddViewModel(
    checklist: Checklist,
    private val insertChecklistUseCase: InsertChecklistUseCase,
    private val updateChecklistUseCase: UpdateChecklistUseCase
) : BaseViewModel(), DragAndDropListener {

    private val _state = MutableLiveData<State<None>>()
    private val _items = MutableLiveData(checklist.lines)

    val state: LiveData<State<None>> = _state
    val items: LiveData<List<Item>> = _items
    val lineItemText = MutableLiveData("")
    var draggedFromTo = Pair(-1, -1)

    fun addItemClicked() {
        val text = lineItemText.value
        if (text.isNullOrEmpty()) return

        val newItems = listOf(Item.create(text)) + (_items.value ?: emptyList())
        _items.postValue(newItems)
        lineItemText.postValue("")
    }

    fun submitClicked() {
        Timber.tag("KUBA").v("submitCLicked")
    }

    private fun addChecklist(checklist: Checklist) {
        insertChecklistUseCase.execute(viewModelScope, checklist) {
            it.either(::handleFailure, ::handleSuccess)
        }
    }

    private fun updateChecklist(checklist: Checklist) {
        updateChecklistUseCase.execute(viewModelScope, checklist) {
            it.either(::handleFailure, ::handleSuccess)
        }
    }

    private fun handleSuccess(rowsAffectedCount: Int) {
        Timber.tag("KUBA").v("handleSuccessUpdate $rowsAffectedCount")
    }

    private fun handleSuccess(rowsAffectedCount: Long) {
        Timber.tag("KUBA").v("handleSuccessInsert $rowsAffectedCount")
    }

    private fun handleFailure(error: Exception) {
        _state.value = State.Error(error)
    }

    fun onDeleteClicked(model: Item) {
        val newItems = (_items.value ?: emptyList()) - model
        _items.postValue(newItems)
    }

    override fun moveItem(from: Int, to: Int) {
        val newItems = (_items.value ?: emptyList()).toMutableList()
        val fromItem = newItems[from]

        newItems.removeAt(from)
        if (to < from) {
            newItems.add(to, fromItem)
        } else {
            newItems.add(to - 1, fromItem)
        }

        draggedFromTo = Pair(from, to)
        _items.postValue(newItems)
    }

    companion object {
        val IS_NOT_DRAGGING = Pair(-1, -1)
    }
}
