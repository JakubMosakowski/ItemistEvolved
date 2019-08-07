package com.jakmos.itemistevolved.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.ItemistResult
import com.jakmos.itemistevolved.domain.model.None
import com.jakmos.itemistevolved.domain.useCase.GetChecklistsUseCase
import timber.log.Timber

class MainActivityViewModel(
    private val getChecklistsUseCase: GetChecklistsUseCase
) : ViewModel() {

    private lateinit var checklists: MutableLiveData<List<Checklist>>

    fun getChecklists(): LiveData<List<Checklist>> {
        val result = getChecklistsUseCase.execute(None())
        checklists = when (result) {
            is ItemistResult.Success -> result.data

            is ItemistResult.Error -> {
                showError(result.error)
                MutableLiveData()
            }
        }

        return checklists
    }

    private fun showError(e: Exception) {
        //todo add showing error
        Timber.tag("KUBA").v("showError $e")
    }
}