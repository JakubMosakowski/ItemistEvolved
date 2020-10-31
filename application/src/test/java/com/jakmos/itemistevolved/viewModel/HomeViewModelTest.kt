package com.jakmos.itemistevolved.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import com.jakmos.itemistevolved.CoroutinesTestRule
import com.jakmos.itemistevolved.TestData
import com.jakmos.itemistevolved.domain.manager.ChecklistDomainManager
import com.jakmos.itemistevolved.presentation.main.home.HomeViewModel
import com.jakmos.itemistevolved.utility.livedata.observeForTesting
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner.StrictStubs


@ExperimentalCoroutinesApi
@RunWith(value = StrictStubs::class)
class HomeViewModelTest {

  //region ViewModel

  private lateinit var viewModel: HomeViewModel

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
    private val CHECKLIST_TO_BE_DELETED = CHECKLISTS[0]
  }

  @Before
  fun setUp() {

    // Stub manager.
    Mockito
      .doReturn(MutableLiveData(CHECKLISTS))
      .`when`<ChecklistDomainManager>(checklistManager).observeChecklists()

    // Build view model.
    viewModel = HomeViewModel(checklistManager)
  }

  //endregion

  //region Delete

  /**
   * Checklists should be removed from list (but not from db) after user clicks delete.
   */
  @Test
  fun `Delete clicked`() = viewModel.checklists.observeForTesting {

    // Given.
    val checklistsAfterRemoval = CHECKLISTS.minus(CHECKLIST_TO_BE_DELETED)

    // When.
    viewModel.onDeleteClicked(CHECKLIST_TO_BE_DELETED)

    // Then.
    assertThat(viewModel.checklists.value)
      .containsExactlyElementsIn(checklistsAfterRemoval)
  }

  /**
   * Checklist shouldn't be deleted if undo was clicked.
   */
  @Test
  fun `Undo delete checklists`() = viewModel.checklists.observeForTesting {

    // When.
    viewModel.onDeleteClicked(CHECKLIST_TO_BE_DELETED)
    viewModel.onUndoClicked()

    // Then.
    assertThat(viewModel.checklists.value)
      .containsExactlyElementsIn(CHECKLISTS)
  }

  /**
   * Checklists should be deleted after snackbar dismisses.
   */
  @Test
  fun `Delete checklists`() = viewModel.checklists.observeForTesting {

    // Given.
    val checklistsAfterRemoval = CHECKLISTS.minus(CHECKLIST_TO_BE_DELETED)

    // When.
    viewModel.onDeleteClicked(CHECKLIST_TO_BE_DELETED)
    viewModel.onSnackbarDismiss()

    // Then.
    assertThat(viewModel.checklists.value)
      .containsExactlyElementsIn(checklistsAfterRemoval)
  }

  //endregion
}
