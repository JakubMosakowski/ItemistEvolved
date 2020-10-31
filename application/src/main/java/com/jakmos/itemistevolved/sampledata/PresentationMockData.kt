package com.jakmos.itemistevolved.sampledata

import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.Subsection
import org.joda.time.DateTime
import kotlin.random.Random

class PresentationMockData {

  companion object {

    //region Checklist

    val CHECKLISTS = (0..2).map {
      val i = it.toLong()
      val subsections = (0..i).map { j ->
        Subsection(text = "Subsection$j", isChecked = Random.nextBoolean())
      }

      Checklist(
        name = "Checklist$i",
        imageUrl = "",
        createdAt = DateTime.now(),
        updatedAt = DateTime.now(),
        subsections = subsections
      )
    }

    //endregion

  }

}
