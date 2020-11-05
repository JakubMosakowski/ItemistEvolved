package com.jakmos.itemistevolved.persistence.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.jakmos.itemistevolved.persistence.database.entity.ChecklistEntity
import com.jakmos.itemistevolved.persistence.database.entity.ChecklistView
import com.jakmos.itemistevolved.utility.vocabulary.Id
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ChecklistDao : BaseDao<ChecklistEntity>() {

  //region Observe - List

  @Transaction
  @Query(value = """
    SELECT * FROM checklist
    ORDER BY createdAt DESC
  """)
  abstract fun observeChecklists(): LiveData<List<ChecklistView>>

  //endregion

  //region Observe - Checklist

  @Transaction
  @Query(value = """
    SELECT * FROM checklist
    WHERE id = :id
    ORDER BY createdAt DESC
  """)
  abstract fun observeChecklist(id: Id): Flow<ChecklistView>

  @Transaction
  @Query(value = """
    SELECT * FROM checklist
    WHERE id = :id
    ORDER BY createdAt DESC 
  """)
  abstract suspend fun getChecklist(id: Id): ChecklistView

  //endregion

  //region Remove

  @Query(value = """
    DELETE FROM checklist
    """)
  abstract suspend fun clearTable()

  //endregion
}
