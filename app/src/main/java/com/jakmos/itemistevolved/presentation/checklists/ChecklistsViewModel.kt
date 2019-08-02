package com.jakmos.itemistevolved.presentation.checklists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChecklistsViewModel : ViewModel() {

    val _clickedCounter = MutableLiveData(0)
    val clickedCounter: LiveData<Int> = _clickedCounter

    fun onClicked() {
        _clickedCounter.value = (_clickedCounter.value ?: 0) + 1
    }
}
