package com.jakmos.itemistevolved.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.jakmos.itemistevolved.domain.model.project.Event

abstract class BaseViewModel : ViewModel() {

    private val _navigationCommands = MutableLiveData<Event<NavigationCommand>>()

    val navigationCommands: LiveData<Event<NavigationCommand>> = _navigationCommands

    sealed class NavigationCommand {
        data class To(val directions: NavDirections) : NavigationCommand()
        object Back : NavigationCommand()
        data class BackTo(val destinationId: Int) : NavigationCommand()
    }

    fun navigate(directions: NavDirections) {
        _navigationCommands.postValue(Event(NavigationCommand.To(directions)))
    }
}