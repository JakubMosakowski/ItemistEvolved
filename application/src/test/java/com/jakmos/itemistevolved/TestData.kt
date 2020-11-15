package com.jakmos.itemistevolved

import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.Subsection
import com.jakmos.itemistevolved.utility.network.remoteconfig.AnimationConfig
import org.joda.time.DateTime

class TestData {

  companion object {

    //region Checklists

    val CHECKLISTS: List<Checklist>
      get() {
        var subsectionId = 0L

        return (0..2).map {
          val i = it.toLong()
          val subsections = (0..i).map { j ->
            Subsection(id = subsectionId++, text = "Subsection$j", isChecked = j % 2L == 0L,
              orderNumber = j)
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

    //region Animation Config

    val ANIMATION_CONFIG: AnimationConfig
      get() = AnimationConfig("RandomUrl", 5)

    //endregion
  }
}
