package com.jakmos.itemistevolved

import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.Item
import com.jakmos.itemistevolved.persistence.cache.database.entity.ChecklistEntity
import java.util.*


val ITEM_1 = Item("ITEM_1", false)
val ITEM_2 = Item("ITEM_2", false)
val ITEM_3 = Item("ITEM_3", true)

val CHECKLIST_1 = Checklist(1, "CHECKLIST_1", "image1", listOf(ITEM_1, ITEM_2), Date())
val CHECKLIST_2 = Checklist(2, "CHECKLIST_2", "image2", listOf(ITEM_1, ITEM_3), Date())
val CHECKLIST_3 = Checklist(3, "CHECKLIST_3", "image3", listOf(ITEM_3, ITEM_2), Date())


val CHECKLIST_ENTITY_1 =
    com.jakmos.itemistevolved.persistence.cache.database.entity.ChecklistEntity(
        CHECKLIST_1.name,
        CHECKLIST_1.image,
        CHECKLIST_1.createdAt!!,
        Date(),
        CHECKLIST_1.lines,
        CHECKLIST_1.id
    )

val CHECKLIST_ENTITY_2 =
    com.jakmos.itemistevolved.persistence.cache.database.entity.ChecklistEntity(
        CHECKLIST_2.name,
        CHECKLIST_2.image,
        CHECKLIST_2.createdAt!!,
        Date(),
        CHECKLIST_2.lines,
        CHECKLIST_2.id
    )
