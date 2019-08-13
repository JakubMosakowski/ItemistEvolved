package com.jakmos.itemistevolved

import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.Item


val ITEM_1 = Item("ITEM_1", false)
val ITEM_2 = Item("ITEM_2", false)
val ITEM_3 = Item("ITEM_3", false)

val CHECKLIST_1 = Checklist(1,"CHECKLIST_1","image1",listOf(ITEM_1,ITEM_2))
val CHECKLIST_2 = Checklist(2,"CHECKLIST_2","image2",listOf(ITEM_1,ITEM_3))
val CHECKLIST_3 = Checklist(3,"CHECKLIST_3","image3",listOf(ITEM_3,ITEM_2))