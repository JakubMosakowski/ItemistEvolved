package com.jakmos.itemistevolved.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jakmos.itemistevolved.persistence.database.converter.DateConverter
import com.jakmos.itemistevolved.persistence.database.dao.ChecklistDao
import com.jakmos.itemistevolved.persistence.database.dao.SubsectionDao
import com.jakmos.itemistevolved.persistence.database.entity.ChecklistEntity
import com.jakmos.itemistevolved.persistence.database.entity.SubsectionEntity


@Database(
  version = 1,
  exportSchema = false,
  entities = [
    ChecklistEntity::class,
    SubsectionEntity::class
  ]
)
@TypeConverters(
  DateConverter::class
)
abstract class AndroidDatabase : RoomDatabase() {

  //region Dao

  abstract fun checklistDao(): ChecklistDao

  abstract fun subsectionDao(): SubsectionDao

  //endregion
}
