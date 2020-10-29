package com.jakmos.itemistevolved

import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.Subsection

val SUBSECTION_1 = Subsection(1, "ITEM_1", false)
val SUBSECTION_2 = Subsection(2, "ITEM_2", false)
val SUBSECTION_3 = Subsection(3, "ITEM_3", true)

val CHECKLIST_1 = Checklist(
  1, "CHECKLIST_1", "image1",
  subsections = listOf(SUBSECTION_1, SUBSECTION_2))
val CHECKLIST_2 = Checklist(
  2, "CHECKLIST_2", "image2",
  subsections = listOf(SUBSECTION_1, SUBSECTION_3))
val CHECKLIST_3 = Checklist(
  3, "CHECKLIST_3", "image3",
  subsections = listOf(SUBSECTION_3, SUBSECTION_2))
