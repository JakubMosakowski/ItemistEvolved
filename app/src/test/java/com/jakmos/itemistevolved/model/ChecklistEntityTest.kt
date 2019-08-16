package com.jakmos.itemistevolved.model

import com.jakmos.itemistevolved.CHECKLIST_1
import com.jakmos.itemistevolved.CHECKLIST_ENTITY_1
import com.jakmos.itemistevolved.domain.model.entity.ChecklistEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class ChecklistEntityTest {

    @Test
    fun test() {
        //Given
        val expected = CHECKLIST_1
        val entity = CHECKLIST_ENTITY_1

        //When
        val result = ChecklistEntity.entityToModel(entity)

        //Then
        assertEquals(expected, result)
    }
}