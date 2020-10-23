package com.jakmos.itemistevolved.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jakmos.itemistevolved.CHECKLIST_3
import com.jakmos.itemistevolved.CoroutinesTestRule
import com.jakmos.itemistevolved.ITEM_3
import com.jakmos.itemistevolved.persistence.cache.database.dao.ChecklistDao
import com.jakmos.itemistevolved.domain.model.project.DateTimeInterface
import com.jakmos.itemistevolved.domain.usecase.GetChecklistsUseCase
import com.jakmos.itemistevolved.domain.usecase.InsertChecklistUseCase
import com.jakmos.itemistevolved.presentation.detail.ChecklistDetailViewModel
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ChecklistDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val dao = mockk<com.jakmos.itemistevolved.persistence.cache.database.dao.ChecklistDao>()
    private val getChecklistsUseCase = spyk(GetChecklistsUseCase(dao))
    private val dateTime = mockk<DateTimeInterface>()
    private val insertChecklistUseCase =
        spyk(InsertChecklistUseCase(dateTime, dao, getChecklistsUseCase))
    private val viewModel by lazy {
        ChecklistDetailViewModel(
            CHECKLIST_3,
            insertChecklistUseCase
        )
    }

    @Test
    fun clearClickedTest() {
        //Given
        val expected = getClearedChecklist()

        //When
        viewModel.clearClicked()

        //Then
        assertEquals(expected, viewModel.checklistLiveData.value)
    }

    @Test
    fun onItemClickedTest() {
        //Given
        val expected = getClearedChecklist()

        //When
        viewModel.onItemClicked(ITEM_3)

        //Then
        assertEquals(expected, viewModel.checklistLiveData.value)
    }

    private fun getClearedChecklist() =
        CHECKLIST_3.copy(lines = CHECKLIST_3.lines.map { it.copy(isChecked = false) })
}
