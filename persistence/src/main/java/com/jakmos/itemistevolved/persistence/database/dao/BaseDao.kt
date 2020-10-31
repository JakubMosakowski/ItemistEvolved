package com.jakmos.itemistevolved.persistence.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
abstract class BaseDao<Entity> {

  //region Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun insert(entity: Entity): Long

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun insert(entities: Iterable<Entity>)

  //endregion

  //region Removed

  @Delete
  abstract suspend fun remove(entity: Entity): Int

  @Delete
  abstract suspend fun remove(entities: Iterable<Entity>): Int

  //endregion

  //region Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun update(entity: Entity): Int

  @Update(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun update(entities: Iterable<Entity>): Int

  //endregion
}
