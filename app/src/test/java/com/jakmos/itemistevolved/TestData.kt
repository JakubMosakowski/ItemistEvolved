package com.jakmos.itemistevolved

import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.Item
import com.jakmos.itemistevolved.domain.model.entity.ChecklistEntity
import java.util.*


val ITEM_1 = Item("ITEM_1", false)
val ITEM_2 = Item("ITEM_2", false)
val ITEM_3 = Item("ITEM_3", false)

val CHECKLIST_1 = Checklist(1, "CHECKLIST_1", "image1", listOf(ITEM_1, ITEM_2))
val CHECKLIST_2 = Checklist(2, "CHECKLIST_2", "image2", listOf(ITEM_1, ITEM_3))
val CHECKLIST_3 = Checklist(3, "CHECKLIST_3", "image3", listOf(ITEM_3, ITEM_2))


val CHECKLIST_ENTITY_1 =
    ChecklistEntity(CHECKLIST_1.id, CHECKLIST_1.name, CHECKLIST_1.image, Date(), Date(), CHECKLIST_1.lines)

val CHECKLIST_ENTITY_2 =
    ChecklistEntity(CHECKLIST_2.id, CHECKLIST_2.name, CHECKLIST_2.image, Date(), Date(), CHECKLIST_2.lines)