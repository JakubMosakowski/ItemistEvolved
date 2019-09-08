package com.jakmos.itemistevolved.model

import com.jakmos.itemistevolved.CHECKLIST_1
import com.jakmos.itemistevolved.CHECKLIST_3
import org.junit.Assert
import org.junit.Test

class ChecklistTest {

    @Test
    fun atLeastOneClickedTest() {
        //Given

        //When
        val result1ShouldBeFalse = CHECKLIST_1.atLeastOneClicked()
        val result2ShouldBeTrue = CHECKLIST_3.atLeastOneClicked()

        //Then
        Assert.assertEquals(false, result1ShouldBeFalse)
        Assert.assertEquals(true, result2ShouldBeTrue)
    }

    @Test
    fun getCounterTextTest() {
        //Given
        val expected = "1 / 2"

        //When
        val result = CHECKLIST_3.getCounterText()

        //Then
        Assert.assertEquals(expected, result)
    }
}
