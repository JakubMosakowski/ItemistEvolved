package com.jakmos.itemistevolved.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.jakmos.itemistevolved.CoroutinesTestRule
import com.jakmos.itemistevolved.TestData
import com.jakmos.itemistevolved.domain.manager.ChecklistDomainManager
import com.jakmos.itemistevolved.presentation.main.add.AddViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner.StrictStubs


@ExperimentalCoroutinesApi
@RunWith(value = StrictStubs::class)
class AddViewModelTest {

  //region ViewModel

  private lateinit var viewModel: AddViewModel

  @Mock
  private lateinit var checklistManager: ChecklistDomainManager

  //endregion

  //region Rules

  @get:Rule
  val rule = InstantTaskExecutorRule()

  @get:Rule
  val coroutinesRule = CoroutinesTestRule()

  //endregion

  //region Setup

  companion object {
    private val CHECKLISTS = TestData.CHECKLISTS
    private val CHECKLIST_TO_BE_EDITED = CHECKLISTS[0]
  }

  @Before
  fun setUp() {

    // Build view model.
    viewModel = AddViewModel(checklistManager)
  }

  //endregion

  //region Initialize

  /**
   * Verify if fields are empty after initialization creation of checklist
   */
  @Test
  fun `Create checklist view model`() {

    // Given.

    // When.
    viewModel.onChecklistAvailable(null)

    // Then.
    Truth.assertThat(viewModel.titleText.value)
      .isEqualTo("")

    Truth.assertThat(viewModel.subsections.value)
      .isEqualTo(null)
  }

  //endregion

  /**
   * Verify if fields are filling with correct data on edit checklist initialization.
   */
  @Test
  fun `Edit checklist view model`() {

    // Given.

    // When.
    viewModel.onChecklistAvailable(CHECKLIST_TO_BE_EDITED)

    // Then.
    Truth.assertThat(viewModel.titleText.value)
      .isEqualTo(CHECKLIST_TO_BE_EDITED.name)

    Truth.assertThat(viewModel.subsections.value)
      .isEqualTo(CHECKLIST_TO_BE_EDITED.subsections)
  }

  //endregion
}


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
