package com.jakmos.itemistevolved.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.Item
import com.jakmos.itemistevolved.domain.useCase.InsertChecklistUseCase
import com.jakmos.itemistevolved.presentation.base.BaseViewModel

class ChecklistDetailViewModel(
    checklist: Checklist,
    private val insertChecklistUseCase: InsertChecklistUseCase
) : BaseViewModel(), CheckboxAdapter.CheckboxAdapterListener {

    private val _checklist = MutableLiveData(checklist)
    val checklistLiveData: LiveData<Checklist> = _checklist

    //TODO write tests

    fun clearClicked() {
        val lines = getClearedLines()
        _checklist.value = _checklist.value?.copy(lines = lines)
        saveToDb()
    }

    private fun saveToDb() {
        val updatedChecklist = _checklist.value ?: return
        insertChecklistUseCase.execute(viewModelScope, updatedChecklist) {
            it.either({}, {})
        }
    }

    private fun getClearedLines() =
        _checklist.value?.lines?.map { it.copy(isChecked = false) } ?: emptyList()

    override fun onItemClicked(model: Item) {
        _checklist.value = getUpdatedChecklist(model)
        saveToDb()
    }

    private fun getUpdatedChecklist(model: Item): Checklist? {
        var items = _checklist.value?.lines ?: emptyList()

        items = items.map {
            return@map if (it == model)
                it.copy(isChecked = !it.isChecked)
            else
                it
        }
        return _checklist.value?.copy(lines = items)
    }
}
