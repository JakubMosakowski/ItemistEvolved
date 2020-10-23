package com.jakmos.itemistevolved.persistence.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface BaseDao<Entity> {

  //region Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(entity: Entity): Long

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(entities: Iterable<Entity>): Long

  //endregion

  //region Removed

  @Delete
  suspend fun remove(entity: Entity): Long

  @Delete
  suspend fun remove(entities: Iterable<Entity>): Long

  //endregion

  //region Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  suspend fun update(entity: Entity): Long

  @Update(onConflict = OnConflictStrategy.REPLACE)
  suspend fun update(entities: Iterable<Entity>): Long

  //endregion
}
