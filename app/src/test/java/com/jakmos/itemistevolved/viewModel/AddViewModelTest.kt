package com.jakmos.itemistevolved.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jakmos.itemistevolved.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.Rule

@ExperimentalCoroutinesApi
class AddViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun getState() {
        TODO("not implemented")
    }
}