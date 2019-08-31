package com.jakmos.itemistevolved.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.jakmos.itemistevolved.domain.model.project.Event

abstract class BaseViewModel : ViewModel() {

    private val _navigationCommands = MutableLiveData<Event<NavigationCommand>>()
    private val _keyboardCommands = MutableLiveData<Event<KeyboardCommand>>()

    val navigationCommands: LiveData<Event<NavigationCommand>> = _navigationCommands
    val keyboardCommands: LiveData<Event<KeyboardCommand>> = _keyboardCommands

    sealed class NavigationCommand {
        data class To(val directions: NavDirections) : NavigationCommand()
        object Back : NavigationCommand()
        data class BackTo(val destinationId: Int) : NavigationCommand()
    }

    fun navigate(directions: NavDirections) {
        _navigationCommands.postValue(Event(NavigationCommand.To(directions)))
    }

    fun hideKeyboard() {
        _keyboardCommands.postValue(Event(KeyboardCommand.Hide))
    }

    fun showKeyboard() {
        _keyboardCommands.postValue(Event(KeyboardCommand.Show))
    }

    sealed class KeyboardCommand {
        object Show : KeyboardCommand()
        object Hide : KeyboardCommand()
    }
}
