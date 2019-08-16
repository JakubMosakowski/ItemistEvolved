package com.jakmos.itemistevolved.useCase

import com.jakmos.itemistevolved.*
import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.model.entity.ChecklistEntity
import com.jakmos.itemistevolved.domain.model.project.DateTimeInterface
import com.jakmos.itemistevolved.domain.model.project.None
import com.jakmos.itemistevolved.domain.useCase.GetChecklistsUseCase
import com.jakmos.itemistevolved.domain.useCase.InsertChecklistUseCase
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
class GetChecklistsUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()
    private val dao = mockk<ChecklistDao>()
    private val useCase by lazy { GetChecklistsUseCase(dao) }

    @Test
    fun executeSuccess() = coroutinesTestRule.testDispatcher.runBlockingTest {
        //Given
        val daoResult = listOf(CHECKLIST_ENTITY_1, CHECKLIST_ENTITY_2)
        val expected =
            listOf(ChecklistEntity.entityToModel(CHECKLIST_ENTITY_1), ChecklistEntity.entityToModel(CHECKLIST_ENTITY_2))
        coEvery { dao.getAll() } returns daoResult

        //When
        val result = useCase.doWork(None())

        //Then
        assertEquals(expected, result)
    }
}