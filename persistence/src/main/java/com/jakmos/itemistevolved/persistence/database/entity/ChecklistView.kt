package com.jakmos.itemistevolved.persistence.database.entity

import androidx.room.Embedded
import androidx.room.Relation

class ChecklistView {

  //region Checklist Entity

  @Embedded
  var checklistEntity: ChecklistEntity = ChecklistEntity()

  //endregion

  //region Items

  @Relation(
    entity = SubsectionEntity::class,
    parentColumn = "id",
    entityColumn = "checklist_id"
  )
  var subsections: List<SubsectionEntity> = emptyList()

  //endregion
}
