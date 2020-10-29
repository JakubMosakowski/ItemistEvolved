package com.jakmos.itemistevolved.persistence.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.jakmos.itemistevolved.persistence.database.entity.SubsectionEntity

@Dao
abstract class SubsectionDao : BaseDao<SubsectionEntity>() {

  //region Remove

  @Query(value = """
    DELETE FROM checklist
    """)
  abstract fun clearTable()

  //endregion
}
