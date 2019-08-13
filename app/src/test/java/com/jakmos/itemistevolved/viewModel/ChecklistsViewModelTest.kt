package com.jakmos.itemistevolved.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jakmos.itemistevolved.CHECKLIST_1
import com.jakmos.itemistevolved.CHECKLIST_2
import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.model.project.State
import com.jakmos.itemistevolved.domain.useCase.GetChecklistsUseCase
import com.jakmos.itemistevolved.presentation.checklists.ChecklistsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import java.io.IOException

@ExperimentalCoroutinesApi
class ChecklistsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    private val dao = mockk<ChecklistDao>()
    private val useCase = spyk(GetChecklistsUseCase(dao))
    private val viewModel by lazy { ChecklistsViewModel(useCase) }

    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun loadDataSuccess() {
        //Given
        coEvery { useCase.doWork(any()) } returns listOf(CHECKLIST_1, CHECKLIST_2)

        //When
        val newViewModel = ChecklistsViewModel(useCase)

        //Then
        assert(newViewModel.state.value?.isSuccess() ?: false)
        assert((newViewModel.state.value as? State.Success)?.data == listOf(CHECKLIST_1, CHECKLIST_2))
    }

    @Test
    fun loadDataFailure() {
        //Given
        coEvery { useCase.doWork(any()) } throws (IOException())

        //When
        val newViewModel = ChecklistsViewModel(useCase)

        //Then
        assert((newViewModel.state.value as? State.Error)?.error is IOException)
    }

    @Test
    fun loadDataEmpty() {
        //Given
        coEvery { useCase.doWork(any()) } returns emptyList()

        //When
        val newViewModel = ChecklistsViewModel(useCase)

        //Then
        assert(newViewModel.state.value?.isEmpty() ?: false)
    }

    @Test
    fun onItemClicked() {
        //TODO

        //Given

        //When
        viewModel.onItemClicked(CHECKLIST_1)

        //Then
    }

    @Test
    fun onEditClicked() {
        //TODO think how to navigate to other fragment onClick with mvvm and binding

        //Given

        //When
        viewModel.onEditClicked(CHECKLIST_1)

        //Then
    }

}