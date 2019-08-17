package com.jakmos.itemistevolved.useCase

import com.jakmos.itemistevolved.CHECKLIST_1
import com.jakmos.itemistevolved.CoroutinesTestRule
import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.model.entity.ChecklistEntity
import com.jakmos.itemistevolved.domain.model.project.DateTimeInterface
import com.jakmos.itemistevolved.domain.useCase.UpdateChecklistUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class UpdateChecklistUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()
    private val dao = mockk<ChecklistDao>()
    private val dateTime = mockk<DateTimeInterface>()
    private val useCase by lazy { UpdateChecklistUseCase(dateTime, dao) }

    @Test
    fun executeSuccess() = coroutinesTestRule.testDispatcher.runBlockingTest {
        //Given
        val param = CHECKLIST_1
        val currentDate = Date()
        every { dateTime.date } returns currentDate
        val expected = 1

        val result = ChecklistEntity(param.name, param.image, param.createdAt!!, currentDate, param.lines, param.id)
        coEvery { dao.updateChecklist(result) } returns expected

        //When
        val returned = useCase.doWork(param)

        //Then
        assertEquals(expected, returned)
    }
}