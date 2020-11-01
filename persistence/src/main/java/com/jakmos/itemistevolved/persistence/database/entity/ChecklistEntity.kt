package com.jakmos.itemistevolved.persistence.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import co.windly.limbo.utility.primitives.EMPTY
import co.windly.limbo.utility.primitives.ZERO
import com.jakmos.itemistevolved.utility.vocabulary.Id
import org.joda.time.DateTime

@Entity(tableName = "checklist")
data class ChecklistEntity(

  //region Id

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  var id: Id = Id.ZERO,

  //endregion

  //region Name
  @ColumnInfo(name = "name")
  var name: String = String.EMPTY,

  //endregion

  //region Image
  @ColumnInfo(name = "imageUrl")
  var imageUrl: String = String.EMPTY,

  //endregion

  //region Created At
  @ColumnInfo(name = "createdAt")
  var createdAt: DateTime = DateTime(),

  //endregion

  //region Subsection

  @Ignore
  var subsections: List<SubsectionEntity> = emptyList()

  //endregion
)
