package com.jakmos.itemistevolved.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.jakmos.itemistevolved.CoroutinesTestRule
import com.jakmos.itemistevolved.TestData.Companion.CHECKLISTS
import com.jakmos.itemistevolved.domain.manager.ChecklistDomainManager
import com.jakmos.itemistevolved.presentation.main.checklist.ChecklistViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner.StrictStubs


@ExperimentalCoroutinesApi
@RunWith(value = StrictStubs::class)
class ChecklistViewModelTest {

  //region ViewModel

  private lateinit var viewModel: ChecklistViewModel

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
    private val checklist = CHECKLISTS[2]
  }

  @Before
  fun setUp() {

    // Stub manager.
    Mockito
      .doReturn(flowOf(checklist))
      .`when`<ChecklistDomainManager>(checklistManager).observeChecklist(checklist.id)

    // Build view model.
    viewModel = ChecklistViewModel(checklistManager)

    // Initialize view model.
    viewModel.onChecklistAvailable(checklist.id)
  }

  //endregion

  //region Initialize

  /**
   * Verify if title sets correctly on initialize.
   */
  @Test
  fun `Create checklist view model`() {

    // Given.
    val expectedTitle = "(2/3) Checklist2"

    // When.
    viewModel.onChecklistUpdated()

    // Then.
    Truth.assertThat(viewModel.title.value)
      .isEqualTo(expectedTitle)
  }

  //endregion

  //region Clear

  /**
   * Verify if checklist updates correctly on clear clicked.
   */
  @Test
  fun `Click clear button`() {

    // Given.
    val expectedTitle = "(0/3) Checklist2"

    // When.
    viewModel.onClearClicked()
    viewModel.onChecklistUpdated()

    // Then.
    Truth.assertThat(viewModel.title.value)
      .isEqualTo(expectedTitle)
  }

  //endregion

  //region Select

  /**
   * Verify if checklist updates correctly on selection.
   */
  @Test
  fun `Select subsection`() {

    // Given.
    val subsection = checklist.subsections[1]
    val expectedTitle = "(3/3) Checklist2"

    // When.
    viewModel.onSubsectionClicked(subsection)
    viewModel.onChecklistUpdated()

    // Then.
    Truth.assertThat(viewModel.title.value)
      .isEqualTo(expectedTitle)
  }

  /**
   * Verify if checklist updates correctly on deselection.
   */
  @Test
  fun `Deselect subsection`() {

    // Given.
    val subsection = checklist.subsections[0]
    val expectedTitle = "(1/3) Checklist2"

    // When.
    viewModel.onSubsectionClicked(subsection)
    viewModel.onChecklistUpdated()

    // Then.
    Truth.assertThat(viewModel.title.value)
      .isEqualTo(expectedTitle)
  }

  //endregion
}
