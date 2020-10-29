package com.jakmos.itemistevolved.viewModel
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import com.jakmos.itemistevolved.CHECKLIST_1
//import com.jakmos.itemistevolved.CoroutinesTestRule
//import com.jakmos.itemistevolved.SUBSECTION_1
//import com.jakmos.itemistevolved.SUBSECTION_2
//import com.jakmos.itemistevolved.persistence.cache.database.dao.ChecklistDao
//import com.jakmos.itemistevolved.domain.model.Checklist
//import com.jakmos.itemistevolved.domain.model.project.DateTimeInterface
//import com.jakmos.itemistevolved.domain.model.project.Event
//import com.jakmos.itemistevolved.domain.model.project.State
//import com.jakmos.itemistevolved.domain.usecase.GetChecklistsUseCase
//import com.jakmos.itemistevolved.domain.usecase.InsertChecklistUseCase
//import com.jakmos.itemistevolved.presentation.main.add.AddFragmentDirections
//import com.jakmos.itemistevolved.presentation.main.add.AddViewModel
//import com.jakmos.itemistevolved.presentation.base.BaseViewModel
//import io.mockk.coEvery
//import io.mockk.every
//import io.mockk.mockk
//import io.mockk.spyk
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Test
//import org.junit.Rule
//import java.lang.Exception
//import java.util.*
//
//@ExperimentalCoroutinesApi
//class AddViewModelTest {
//
//    @get:Rule
//    val rule = InstantTaskExecutorRule()
//    @get:Rule
//    val coroutinesTestRule = CoroutinesTestRule()
//
//    private val dao = mockk<com.jakmos.itemistevolved.persistence.cache.database.dao.ChecklistDao>()
//    private val getChecklistsUseCase = spyk(GetChecklistsUseCase(dao))
//    private val dateTime = mockk<DateTimeInterface>()
//    private val insertChecklistUseCase =
//        spyk(InsertChecklistUseCase(dateTime, dao, getChecklistsUseCase))
//    private lateinit var viewModel: AddViewModel
//    private val newChecklist = Checklist.create()
//
//    @Before
//    fun before() {
//        val currentDate = Date()
//        every { dateTime.date } returns currentDate
//        viewModel = AddViewModel(newChecklist, insertChecklistUseCase)
//
//        viewModel.lineItemText.postValue(CHECKLIST_1.lines[0].text)
//        viewModel.addItemClicked()
//
//        viewModel.lineItemText.postValue(CHECKLIST_1.lines[1].text)
//        viewModel.addItemClicked()
//        viewModel.lineItemText.postValue("")
//    }
//
//    @Test
//    fun initVMTest() {
//        //Given
//
//        //When
//        val viewModel = AddViewModel(Checklist.create(), insertChecklistUseCase)
//
//        //Then
//        assertEquals(Event(BaseViewModel.KeyboardCommand.Show), viewModel.keyboardCommands.value)
//    }
//
//    @Test
//    fun addItemClickedTest() {
//        //Given
//        val expected = CHECKLIST_1.lines + listOf(SUBSECTION_1)
//        viewModel.lineItemText.postValue(SUBSECTION_1.text)
//
//        //When
//        viewModel.addItemClicked()
//
//        //Then
//        assertEquals(expected, viewModel.items.value)
//    }
//
//    @Test
//    fun insertSubmitClickedTestSuccess() {
//        //Given
//        viewModel.titleText.postValue("NEW CHECKLIST")
//        val checklistWithTitle =
//            newChecklist.copy(name = "NEW CHECKLIST", lines = CHECKLIST_1.lines.asReversed())
//        coEvery { insertChecklistUseCase.doWork(checklistWithTitle) } returns listOf(
//            checklistWithTitle
//        )
//        val expected = AddFragmentDirections.actionAddFragmentToChecklistsFragment()
//
//        //When
//        viewModel.submitClicked()
//
//        //Then
//        assertEquals(
//            expected,
//            (viewModel.navigationCommands.value?.peekContent() as? BaseViewModel.NavigationCommand.To)?.directions
//        )
//    }
//
//    @Test
//    fun updateSubmitClickedTestSuccess() {
//        //Given
//        val viewModel = AddViewModel(CHECKLIST_1, insertChecklistUseCase)
//        viewModel.titleText.postValue("UPDATED")
//        val updated = CHECKLIST_1.copy(name = "UPDATED")
//        coEvery { insertChecklistUseCase.doWork(updated) } returns listOf(updated)
//        val expected = AddFragmentDirections.actionAddFragmentToChecklistsFragment()
//
//        //When
//        viewModel.submitClicked()
//
//        //Then
//        assertEquals(
//            expected,
//            (viewModel.navigationCommands.value?.peekContent() as? BaseViewModel.NavigationCommand.To)?.directions
//        )
//    }
//
//    @Test
//    fun submitClickedTestFailure() {
//        //Given
//        val viewModel = AddViewModel(CHECKLIST_1, insertChecklistUseCase)
//        val error = Exception("SOMETHING WENT WRONG")
//        coEvery { insertChecklistUseCase.doWork(CHECKLIST_1) } throws error
//
//        //When
//        viewModel.submitClicked()
//
//        //Then
//        assertEquals(error, (viewModel.state.value as State.Error).error)
//    }
//
//    @Test
//    fun onDeleteClickedTest() {
//        //Given
//        val expected = listOf(SUBSECTION_2)
//
//        //When
//        viewModel.onDeleteClicked(SUBSECTION_1)
//
//        //Then
//        assertEquals(expected, viewModel.items.value)
//    }
//
//    @Test
//    fun moveItemTest() {
//        //Given
//        val expected = listOf(SUBSECTION_2, SUBSECTION_1)
//
//        //When
//        viewModel.moveItem(0, 1)
//
//        //Then
//        assertEquals(expected, viewModel.items.value)
//    }
//}
