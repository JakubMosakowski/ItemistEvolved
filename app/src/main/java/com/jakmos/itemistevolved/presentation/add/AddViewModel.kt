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
import timber.log.Timber

class AddViewModel(
    checklist: Checklist,
    private val insertChecklistUseCase: InsertChecklistUseCase,
    private val updateChecklistUseCase: UpdateChecklistUseCase
) : BaseViewModel(), ItemAdapter.ItemAdapterListener {

    private val _state = MutableLiveData<State<None>>()
    private val _items = MutableLiveData(checklist.lines)

    val state: LiveData<State<None>> = _state
    val items: LiveData<List<Item>> = _items

    fun addItemClicked(text: String) {
        if(text == "") return

        val newItems = (_items.value ?: emptyList()) + Item.create(text)
        _items.postValue(newItems)
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

    override fun onDeleteClicked(model: Item) {
        val newItems = (_items.value ?: emptyList()) - model
        _items.postValue(newItems)
    }
}
