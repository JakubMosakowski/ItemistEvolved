package com.jakmos.itemistevolved.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavDirections
import com.jakmos.itemistevolved.domain.model.project.Event
import com.jakmos.itemistevolved.presentation.base.BaseViewModel
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Rule

@ExperimentalCoroutinesApi
class BaseViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val viewModel = spyk<BaseViewModel>()
    private val directions = spyk<NavDirections>()

    @Test
    fun navigate() {
        //Given
        val expected = Event(BaseViewModel.NavigationCommand.To(directions))

        //When
        viewModel.navigate(directions)

        //Then
        assertEquals(expected, viewModel.navigationCommands.value)
    }

    @Test
    fun showKeyboard(){
        //Given
        val expected = Event(BaseViewModel.KeyboardCommand.Show)

        //When
        viewModel.showKeyboard()

        //Then
        assertEquals(expected, viewModel.keyboardCommands.value)
    }

    @Test
    fun hideKeyboard(){
        //Given
        val expected = Event(BaseViewModel.KeyboardCommand.Hide)

        //When
        viewModel.hideKeyboard()

        //Then
        assertEquals(expected, viewModel.keyboardCommands.value)
    }
}
