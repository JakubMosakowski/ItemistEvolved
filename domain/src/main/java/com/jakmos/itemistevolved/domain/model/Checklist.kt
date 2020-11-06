package com.jakmos.itemistevolved.domain.model

import co.windly.limbo.utility.primitives.EMPTY
import co.windly.limbo.utility.primitives.ZERO
import com.jakmos.itemistevolved.utility.vocabulary.Id
import org.joda.time.DateTime

data class Checklist(

  //region Id

  var id: Id = Id.ZERO,

  //endregion

  //region Name

  var name: String = String.EMPTY,

  //endregion

  //region Image Url

  var imageUrl: String = String.EMPTY,

  //endregion

  //region Created At

  var createdAt: DateTime = DateTime(),

  //endregion

  //region Subsection

  var subsections: List<Subsection> = emptyList()

  //endregion
) {

  fun getNumberOfSelectedSubsections(): Int =
    subsections.count { it.isChecked }

  fun selectSubsection(subsection: Subsection) {
    subsections.find { it.id == subsection.id }?.isChecked = !subsection.isChecked
  }

  fun deselectAllSubsections() =
    subsections.forEach {
      it.isChecked = false
    }

}
