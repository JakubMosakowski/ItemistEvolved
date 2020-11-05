package com.jakmos.itemistevolved.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.windly.limbo.utility.primitives.EMPTY
import com.google.common.truth.Truth
import com.jakmos.itemistevolved.CoroutinesTestRule
import com.jakmos.itemistevolved.TestData.Companion.CHECKLISTS
import com.jakmos.itemistevolved.domain.manager.ChecklistDomainManager
import com.jakmos.itemistevolved.domain.model.Subsection
import com.jakmos.itemistevolved.presentation.main.add.AddViewModel
import com.jakmos.itemistevolved.utility.vocabulary.INVALID_ID
import com.jakmos.itemistevolved.utility.vocabulary.Id
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
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
    private val checklistToBeCreated = CHECKLISTS[0]
    private val checklistToBeSaved = CHECKLISTS[1]
    private val checklistToBeEdited = CHECKLISTS[2]
  }

  @Before
  fun setUp() = runBlockingTest {

    // Stub manager.
    Mockito
      .doThrow(IllegalArgumentException("Not found"))
      .`when`<ChecklistDomainManager>(checklistManager).getChecklist(Id.INVALID_ID)

    Mockito
      .doReturn(checklistToBeSaved)
      .`when`<ChecklistDomainManager>(checklistManager).getChecklist(checklistToBeSaved.id)

    Mockito
      .doReturn(checklistToBeEdited)
      .`when`<ChecklistDomainManager>(checklistManager).getChecklist(checklistToBeEdited.id)

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
    viewModel.onChecklistAvailable(Id.INVALID_ID)

    // Then.
    Truth.assertThat(viewModel.titleText.value)
      .isEqualTo("")

    Truth.assertThat(viewModel.subsections.value)
      .isEqualTo(null)
  }

  /**
   * Verify if fields are filling with correct data on edit checklist initialization.
   */
  @Test
  fun `Edit checklist view model`() {

    // Given.

    // When.
    viewModel.onChecklistAvailable(checklistToBeEdited.id)

    // Then.
    Truth.assertThat(viewModel.titleText.value)
      .isEqualTo(checklistToBeEdited.name)

    Truth.assertThat(viewModel.subsections.value)
      .isEqualTo(checklistToBeEdited.subsections)
  }

  //endregion

  //region Add

  /**
   * Verify if new subsection is added after click (when checklist is empty).
   */
  @Test
  fun `Add first subsection`() {

    // Given.
    val subsectionToBeAdded = checklistToBeCreated.subsections[0]
    viewModel.subsectionText.postValue(subsectionToBeAdded.text)

    // When.
    viewModel.onAddClicked()

    // Then.
    Truth.assertThat(viewModel.subsections.value)
      .isEqualTo(listOf(Subsection(1, subsectionToBeAdded.text)))

    Truth.assertThat(viewModel.subsectionText.value)
      .isEqualTo(String.EMPTY)
  }

  /**
   * Verify if new subsection is added after click (when checklist is NOT empty).
   */
  @Test
  fun `Add the following subsection`() {

    // Given
    val newSubsectionText = "New subsection"
    val newIndex = checklistToBeEdited.subsections.maxBy { it.id }!!.id + 1L

    viewModel.onChecklistAvailable(checklistToBeEdited.id)
    viewModel.subsectionText.postValue(newSubsectionText)

    // When.
    viewModel.onAddClicked()

    // Then.
    Truth.assertThat(viewModel.subsections.value)
      .isEqualTo(checklistToBeEdited.subsections + Subsection(newIndex, newSubsectionText))
  }

  //endregion

  //region Delete

  /**
   * Verify if new subsection is deleted after click.
   */
  @Test
  fun `Delete subsection`() {

    // Given.
    val subsectionToBeRemoved = checklistToBeEdited.subsections[0]
    val expectedResult = checklistToBeEdited.subsections - subsectionToBeRemoved
    viewModel.onChecklistAvailable(checklistToBeEdited.id)

    // When.
    viewModel.onDeleteClicked(subsectionToBeRemoved)

    // Then.
    Truth.assertThat(viewModel.subsections.value)
      .isEqualTo(expectedResult)
  }

  //endregion

  //region Move

  /**
   * Verify if new order of subsections is saved after move.
   */
  @Test
  fun `Reorder subsections`() {

    // Given.
    val newOrderOfSubsections = checklistToBeEdited.subsections.shuffled()
    viewModel.onChecklistAvailable(checklistToBeEdited.id)

    // When.
    viewModel.onSubsectionsReordered(newOrderOfSubsections)

    // Then.
    Truth.assertThat(viewModel.subsections.value)
      .isEqualTo(newOrderOfSubsections)
  }

  //endregion

  //region Save

  /**
   * Verify if new checklist is saved after click.
   */
  @Test
  fun `Save new checklist`() = runBlockingTest {

    // Given.
    val newChecklistName = "New checklist"
    val newChecklistSubsections = listOf(
      Subsection(1, "Sub1"),
      Subsection(2, "Sub2")
    )

    // Add some data.
    viewModel.titleText.postValue(newChecklistName)
    viewModel.subsectionText.postValue(newChecklistSubsections[0].text)
    viewModel.onAddClicked()
    viewModel.subsectionText.postValue(newChecklistSubsections[1].text)
    viewModel.onAddClicked()

    // When.
    viewModel.onSubmitClicked()

    // Then.
    Mockito
      .verify(checklistManager)
      .addChecklist(
        newChecklistName,
        newChecklistSubsections
      )
  }

  /**
   * Verify if edited checklist is saved after click.
   */
  @Test
  fun `Save edited checklist`() = runBlockingTest {

    // Given.
    viewModel.onChecklistAvailable(checklistToBeSaved.id)

    // When.
    viewModel.onSubmitClicked()

    // Then.
    Mockito
      .verify(checklistManager)
      .addChecklist(
        checklistToBeSaved.name,
        checklistToBeSaved.subsections,
        checklistToBeSaved.id
      )
  }

  //endregion
}
