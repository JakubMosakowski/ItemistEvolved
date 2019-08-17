package com.jakmos.itemistevolved.useCase

import com.jakmos.itemistevolved.CHECKLIST_1
import com.jakmos.itemistevolved.CoroutinesTestRule
import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.useCase.RemoveChecklistUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoveChecklistUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()
    private val dao = mockk<ChecklistDao>()
    private val useCase by lazy { RemoveChecklistUseCase(dao) }

    @Test
    fun executeSuccess() = coroutinesTestRule.testDispatcher.runBlockingTest {
        //Given
        val param = CHECKLIST_1
        val expected = 1
        coEvery { dao.deleteById(CHECKLIST_1.id) } returns expected

        //When
        val returned = useCase.doWork(param)

        //Then
        assertEquals(expected, returned)
    }
}