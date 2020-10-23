package com.jakmos.itemistevolved.persistence.database.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.jakmos.itemistevolved.utility.vocabulary.Id
import com.jakmos.itemistevolved.utility.vocabulary.NONE
import com.jakmos.itemistevolved.utility.vocabulary.ZERO

data class SubsectionEntity(

  //region Id

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  var id: Id = Id.ZERO,

  //endregion

  //region Text

  @ColumnInfo(name = "text")
  var text: String = String.NONE,

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
