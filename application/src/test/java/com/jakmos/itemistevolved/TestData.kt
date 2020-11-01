package com.jakmos.itemistevolved

import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.Subsection
import org.joda.time.DateTime
import kotlin.random.Random

class TestData {

  companion object {

    //region Checklists

    val CHECKLISTS: List<Checklist>
      get() {
        var subsectionId = 0L

        return (0..2).map {
          val i = it.toLong()
          val subsections = (0..i).map { j ->
            Subsection(id = subsectionId++, text = "Subsection$j", isChecked = Random.nextBoolean())
          }

          Checklist(
            id = i,
            name = "Checklist$i",
            imageUrl = "",
            createdAt = DateTime.now(),
            subsections = subsections
          )
        }
      }

    //endregion
  }
}
