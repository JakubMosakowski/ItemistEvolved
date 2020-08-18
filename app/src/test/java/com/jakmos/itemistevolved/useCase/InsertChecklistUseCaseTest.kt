package com.jakmos.itemistevolved.useCase

import com.jakmos.itemistevolved.CHECKLIST_1
import com.jakmos.itemistevolved.CoroutinesTestRule
import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.data.entity.ChecklistEntity
import com.jakmos.itemistevolved.domain.model.project.DateTimeInterface
import com.jakmos.itemistevolved.domain.model.project.None
import com.jakmos.itemistevolved.domain.usecase.GetChecklistsUseCase
import com.jakmos.itemistevolved.domain.usecase.InsertChecklistUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class InsertChecklistUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()
    private val dao = mockk<ChecklistDao>()
    private val dateTime = mockk<DateTimeInterface>()
    private val getChecklistsUseCase = spyk(GetChecklistsUseCase(dao))
    private val insertChecklistUseCase by lazy {
        InsertChecklistUseCase(
            dateTime,
            dao,
            getChecklistsUseCase
        )
    }

    @Test
    fun executeSuccess() = coroutinesTestRule.testDispatcher.runBlockingTest {
        //Given
        val currentDate = Date()
        every { dateTime.date } returns currentDate
        coEvery { getChecklistsUseCase.doWork(None) } returns listOf(CHECKLIST_1)

        val generatedId = 1L
        val param = CHECKLIST_1
        val result = ChecklistEntity(
            param.name,
            param.image,
            param.createdAt!!,
            currentDate,
            param.lines,
            param.id
        )
        coEvery { dao.insert(result) } returns generatedId

        //When
        val returned = insertChecklistUseCase.doWork(param)

        //Then
        Assert.assertEquals(listOf(CHECKLIST_1), returned)
    }
}
