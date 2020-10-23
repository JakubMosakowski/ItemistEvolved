package com.jakmos.itemistevolved.persistence.database.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import co.windly.limbo.utility.primitives.EMPTY
import co.windly.limbo.utility.primitives.ZERO
import com.jakmos.itemistevolved.utility.vocabulary.Id

data class SubsectionEntity(

  //region Id

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  var id: Id = Id.ZERO,

  //endregion

  //region Text

  @ColumnInfo(name = "text")
  var text: String = String.EMPTY,

  //endregion

  //region Is Checked

  @ColumnInfo(name = "isChecked")
  var isChecked: Boolean = false,

  //endregion

  //region Checklist Id

  @ColumnInfo(name = "checklist_id")
  var checklistId: Id = Id.ZERO

  //endregion
)
