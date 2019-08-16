package com.jakmos.itemistevolved.useCase

import com.jakmos.itemistevolved.CHECKLIST_1
import com.jakmos.itemistevolved.CoroutinesTestRule
import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.model.entity.ChecklistEntity
import com.jakmos.itemistevolved.domain.model.project.DateTimeInterface
import com.jakmos.itemistevolved.domain.useCase.InsertChecklistUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Rule
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class InsertChecklistUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()
    private val dao = mockk<ChecklistDao>()
    private val dateTime = mockk<DateTimeInterface>()
    private val useCase by lazy { InsertChecklistUseCase(dateTime, dao) }

    @Test
    fun executeSuccess() = coroutinesTestRule.testDispatcher.runBlockingTest {
        //Given
        val currentDate = Date()
        every { dateTime.date } returns currentDate
        val param = CHECKLIST_1
        val result = ChecklistEntity(param.id, param.name, param.image, currentDate, currentDate, param.lines)
        coEvery { dao.insert(result) } returns Unit

        //When
        useCase.doWork(param)

        //Then
        //is not throwing
    }
}