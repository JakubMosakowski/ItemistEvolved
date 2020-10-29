package com.jakmos.itemistevolved.useCase


//TODO fix tests
//import com.jakmos.itemistevolved.CoroutinesTestRule
//import com.jakmos.itemistevolved.domain.model.project.UseCase
//import io.mockk.coEvery
//import io.mockk.spyk
//import org.junit.Assert.assertEquals
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.TestCoroutineScope
//import org.junit.Rule
//import org.junit.Test
//import java.lang.Exception
//
//@ExperimentalCoroutinesApi
//class UseCaseTest {
//
//    @get:Rule
//    val coroutinesTestRule = CoroutinesTestRule()
//    private val useCase = spyk<UseCase<TestParam, TestData>>()
//
//    @Test
//    fun executeSuccess() {
//        //Given
//        var someValue: Int = INITIAL
//        coEvery { useCase.doWork(any()) } returns TEST_DATA
//
//        //When
//        useCase.execute(TestCoroutineScope(), TEST_PARAM) {
//            it.either({
//                someValue = ERROR
//                return@either Any()
//            }) {
//                someValue = SUCCESS
//                return@either Any()
//            }
//        }
//
//        //Then
//        assertEquals(someValue, SUCCESS)
//    }
//
//    @Test
//    fun executeFailure() {
//        //Given
//        var someValue: Int = INITIAL
//        coEvery { useCase.doWork(any()) } throws Exception()
//
//        //When
//        useCase.execute(TestCoroutineScope(), TEST_PARAM) {
//            it.either({
//                someValue = ERROR
//                return@either Any()
//            }) {
//                someValue = SUCCESS
//                return@either Any()
//            }
//        }
//
//        //Then
//        assertEquals(someValue, ERROR)
//    }
//
//    data class TestParam(val value: String)
//    data class TestData(val value: String)
//
//    companion object {
//
//        private val TEST_DATA = TestData("TEST DATA")
//        private val TEST_PARAM = TestParam("TEST PARAM")
//        private const val SUCCESS = 1
//        private const val ERROR = -1
//        private const val INITIAL = 0
//
//    }
//}
