package com.jakmos.itemistevolved.persistence.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.jakmos.itemistevolved.persistence.database.entity.ChecklistEntity
import com.jakmos.itemistevolved.persistence.database.entity.ChecklistView

@Dao
abstract class ChecklistDao : BaseDao<ChecklistEntity>() {

  //region Observe

  @Transaction
  @Query(value = """
    SELECT * FROM checklist
    ORDER BY updatedAt DESC
  """)
  abstract fun observeChecklists(): LiveData<List<ChecklistView>>

  @Transaction
  @Query(value = """
    SELECT * FROM checklist
    WHERE name LIKE :name
  """)
  abstract fun observeChecklistsByName(name: String): LiveData<List<ChecklistView>>

  //endregion

  //region Remove

  @Query(value = """
    DELETE FROM checklist
    """)
  abstract fun clearTable()

  //endregion
}
