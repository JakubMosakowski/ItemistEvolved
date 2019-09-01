package com.jakmos.itemistevolved.useCase

import com.jakmos.itemistevolved.CHECKLIST_1
import com.jakmos.itemistevolved.CHECKLIST_2
import com.jakmos.itemistevolved.CoroutinesTestRule
import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.model.entity.ChecklistEntity
import com.jakmos.itemistevolved.domain.model.project.None
import com.jakmos.itemistevolved.domain.useCase.GetChecklistsUseCase
import com.jakmos.itemistevolved.domain.useCase.RemoveChecklistUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class RemoveChecklistUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()
    private val dao = mockk<ChecklistDao>()
    private val getChecklistsUseCase = spyk(GetChecklistsUseCase(dao))
    private val removeChecklistUseCase by lazy { RemoveChecklistUseCase(dao, getChecklistsUseCase) }

    @Test
    fun executeSuccess() = coroutinesTestRule.testDispatcher.runBlockingTest {
        //Given
        val param = CHECKLIST_1
        val expected = listOf(CHECKLIST_2)
        coEvery { getChecklistsUseCase.doWork(None) } returns listOf(CHECKLIST_2)
        coEvery { dao.deleteById(CHECKLIST_1.id) } returns 1

        //When
        val returned = removeChecklistUseCase.doWork(param)

        //Then
        assertEquals(expected, returned)
    }
}
