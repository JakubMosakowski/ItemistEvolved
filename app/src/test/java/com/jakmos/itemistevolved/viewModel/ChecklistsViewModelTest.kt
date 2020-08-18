package com.jakmos.itemistevolved.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jakmos.itemistevolved.CHECKLIST_1
import com.jakmos.itemistevolved.CHECKLIST_2
import com.jakmos.itemistevolved.CoroutinesTestRule
import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.project.State
import com.jakmos.itemistevolved.domain.usecase.GetChecklistsUseCase
import com.jakmos.itemistevolved.domain.usecase.RemoveChecklistUseCase
import com.jakmos.itemistevolved.presentation.base.BaseViewModel
import com.jakmos.itemistevolved.presentation.checklists.ChecklistsFragmentDirections
import com.jakmos.itemistevolved.presentation.checklists.ChecklistsViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.Assert.assertEquals
import java.io.IOException

@ExperimentalCoroutinesApi
class ChecklistsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val dao = mockk<ChecklistDao>()
    private val observer: Observer<List<Checklist>> = mockk()
    private val getChecklistsUseCase = spyk(GetChecklistsUseCase(dao))
    private val removeChecklistUseCase = spyk(RemoveChecklistUseCase(dao, getChecklistsUseCase))
    private val viewModel by lazy {
        ChecklistsViewModel(
            getChecklistsUseCase,
            removeChecklistUseCase
        )
    }

    @Before
    fun before() {
        viewModel.checklists.observeForever(observer)
        every { observer.onChanged(any()) } returns Unit
    }

    @Test
    fun loadDataSuccess() {
        //Given
        coEvery { getChecklistsUseCase.doWork(any()) } returns listOf(CHECKLIST_1, CHECKLIST_2)
        val expected = listOf(CHECKLIST_1, CHECKLIST_2)
        //When
        viewModel.loadData()

        //Then
        assertEquals(true, viewModel.state.value?.isSuccess())
        assertEquals(expected, viewModel.checklists.value)
    }

    @Test
    fun loadDataFailure() {
        //Given
        coEvery { getChecklistsUseCase.doWork(any()) } throws (IOException())

        //When
        viewModel.loadData()

        //Then
        assert((viewModel.state.value as? State.Error)?.error is IOException)
    }

    @Test
    fun loadDataEmpty() {
        //Given
        coEvery { getChecklistsUseCase.doWork(any()) } returns emptyList()

        //When
        viewModel.loadData()

        //Then
        assert(viewModel.state.value?.isEmpty() ?: false)
    }

    @Test
    fun onItemClicked() {
        //Given
        val directions =
            ChecklistsFragmentDirections.actionChecklistsFragmentToChecklistDetailFragment(
                CHECKLIST_1
            )

        //When
        viewModel.onItemClicked(CHECKLIST_1)

        //Then
        assert((viewModel.navigationCommands.value?.peekContent() as? BaseViewModel.NavigationCommand.To)?.directions == directions)
    }

    @Test
    fun onEditClicked() {
        //Given
        val directions =
            ChecklistsFragmentDirections.actionChecklistsFragmentToAddFragment(CHECKLIST_1)

        //When
        viewModel.onEditClicked(CHECKLIST_1)

        //Then
        assert((viewModel.navigationCommands.value?.peekContent() as? BaseViewModel.NavigationCommand.To)?.directions == directions)
    }

    @Test
    fun onDeleteClicked() {
        //Given
        coEvery { getChecklistsUseCase.doWork(any()) } returns listOf(CHECKLIST_1, CHECKLIST_2)
        val expected = listOf(CHECKLIST_2)
        viewModel.loadData()

        //When
        viewModel.onDeleteClicked(CHECKLIST_1)

        //Then
        assert(viewModel.state.value is State.Removing)
        assertEquals(expected, viewModel.checklists.value)
    }

    @Test
    fun onUndoClicked() {
        //Given
        coEvery { getChecklistsUseCase.doWork(any()) } returns listOf(CHECKLIST_1, CHECKLIST_2)
        val expected = listOf(CHECKLIST_1, CHECKLIST_2)
        viewModel.loadData()
        viewModel.onDeleteClicked(CHECKLIST_1)

        //When
        viewModel.onUndoClicked()

        //Then
        assert(viewModel.state.value is State.Success)
        assertEquals(expected, viewModel.checklists.value)

    }

    @Test
    fun onSnackbarDismissed() {
        //Given
        coEvery { getChecklistsUseCase.doWork(any()) } returns listOf(CHECKLIST_1, CHECKLIST_2)
        coEvery { removeChecklistUseCase.doWork(any()) } returns listOf(CHECKLIST_2)
        val expected = listOf(CHECKLIST_2)
        viewModel.loadData()
        viewModel.onDeleteClicked(CHECKLIST_1)

        //When
        viewModel.snackbarDismissed()

        //Then
        assert(viewModel.state.value is State.Success)
        assertEquals(expected, viewModel.checklists.value)
    }
}
