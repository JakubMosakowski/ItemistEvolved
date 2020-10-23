package com.jakmos.itemistevolved.persistence.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.jakmos.itemistevolved.utility.vocabulary.Id
import com.jakmos.itemistevolved.utility.vocabulary.NONE
import com.jakmos.itemistevolved.utility.vocabulary.ZERO
import java.util.Date

@Entity(tableName = "checklist")
data class ChecklistEntity(

  //region Id

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  var id: Id = Id.ZERO,

  //endregion

  //region Name
  @ColumnInfo(name = "name")
  var name: String = String.NONE,

  //endregion

  //region Image
  @ColumnInfo(name = "image")
  var image: String = String.NONE,

  //endregion

  //region Created At
  @ColumnInfo(name = "createdAt")
  var createdAt: Date = Date(),

  //endregion

  //region Updated At

  @ColumnInfo(name = "UpdatedAt")
  var updatedAt: Date = Date(),

  //endregion

  //region Subsection

  @Ignore
  var subsections: List<SubsectionEntity> = emptyList()

  //endregion
)
