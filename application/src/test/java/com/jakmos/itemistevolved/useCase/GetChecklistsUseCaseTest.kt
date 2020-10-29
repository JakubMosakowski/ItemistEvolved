package com.jakmos.itemistevolved.useCase

//import com.jakmos.itemistevolved.*
//import com.jakmos.itemistevolved.persistence.cache.database.dao.ChecklistDao
//import com.jakmos.itemistevolved.persistence.cache.database.entity.ChecklistEntity
//import com.jakmos.itemistevolved.domain.model.project.None
//import com.jakmos.itemistevolved.domain.usecase.GetChecklistsUseCase
//import io.mockk.coEvery
//import io.mockk.mockk
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import org.junit.Assert.assertEquals
//import org.junit.Rule
//import org.junit.Test
//
//@ExperimentalCoroutinesApi
//class GetChecklistsUseCaseTest {
//
//    @get:Rule
//    val coroutinesTestRule = CoroutinesTestRule()
//    private val dao = mockk<com.jakmos.itemistevolved.persistence.cache.database.dao.ChecklistDao>()
//    private val useCase by lazy { GetChecklistsUseCase(dao) }
//
//    @Test
//    fun executeSuccess() = coroutinesTestRule.testDispatcher.runBlockingTest {
//        //Given
//        val daoResult = listOf(CHECKLIST_ENTITY_1, CHECKLIST_ENTITY_2)
//        val expected =
//            listOf(com.jakmos.itemistevolved.persistence.cache.database.entity.ChecklistEntity.entityToModel(CHECKLIST_ENTITY_1), com.jakmos.itemistevolved.persistence.cache.database.entity.ChecklistEntity.entityToModel(CHECKLIST_ENTITY_2))
//        coEvery { dao.getAll() } returns daoResult
//
//        //When
//        val result = useCase.doWork(None)
//
//        //Then
//        assertEquals(expected, result)
//    }
//}
