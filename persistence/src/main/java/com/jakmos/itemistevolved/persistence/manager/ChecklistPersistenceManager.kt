package com.jakmos.itemistevolved.persistence.manager

import androidx.lifecycle.LiveData
import com.jakmos.itemistevolved.persistence.database.dao.ChecklistDao
import com.jakmos.itemistevolved.persistence.database.dao.SubsectionDao
import com.jakmos.itemistevolved.persistence.database.entity.ChecklistEntity
import com.jakmos.itemistevolved.persistence.database.entity.ChecklistView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChecklistPersistenceManager @Inject constructor(
  private val checklistDao: ChecklistDao,
  private val subsectionDao: SubsectionDao
) {

  //region Observe

  fun observeChecklists(): LiveData<List<ChecklistView>> =
    checklistDao
      .observeChecklists()

  fun observeChecklistsByName(name: String): LiveData<List<ChecklistView>> =
    checklistDao
      .observeChecklistsByName(name)

  //endregion

  //region Save

  suspend fun saveChecklist(checklist: ChecklistEntity) {

    // Cleanup.
    checklistDao.remove(checklist)
    subsectionDao.remove(checklist.subsections)

    // Insert checklist.
    checklistDao.insert(checklist)

    // Insert subsections.
    subsectionDao.insert(checklist.subsections)
  }

  //endregion
}
