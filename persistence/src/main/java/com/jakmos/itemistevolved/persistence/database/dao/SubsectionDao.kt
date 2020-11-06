package com.jakmos.itemistevolved.persistence.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.jakmos.itemistevolved.persistence.database.entity.SubsectionEntity
import com.jakmos.itemistevolved.utility.vocabulary.Id

@Dao
abstract class SubsectionDao : BaseDao<SubsectionEntity>() {

  //region Remove

  @Query(value = """
    DELETE FROM subsection
    WHERE checklist_id=:id
    """)
  abstract fun removeByChecklistId(id: Id)

  @Query(value = """
    DELETE FROM subsection
    """)
  abstract fun clearTable()

  //endregion
}
