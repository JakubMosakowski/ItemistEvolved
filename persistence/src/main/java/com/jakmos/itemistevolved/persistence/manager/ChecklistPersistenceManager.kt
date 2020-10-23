package com.jakmos.itemistevolved.persistence.manager

import androidx.lifecycle.LiveData
import com.jakmos.itemistevolved.persistence.database.dao.ChecklistDao
import com.jakmos.itemistevolved.persistence.database.dao.SubsectionDao
import com.jakmos.itemistevolved.persistence.database.entity.ChecklistEntity
import com.jakmos.itemistevolved.persistence.database.entity.ChecklistView
import io.reactivex.Completable
import io.reactivex.annotations.SchedulerSupport
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@SchedulerSupport(value = SchedulerSupport.IO)
class ChecklistPersistenceManager @Inject constructor(
  private val checklistDao: ChecklistDao,
  private val subsectionDao: SubsectionDao
) {

  //region Observe

  suspend fun observeChecklists(): LiveData<List<ChecklistView>> =
    checklistDao
      .observeChecklists()

  suspend fun observeChecklistsByName(name: String): LiveData<List<ChecklistView>> =
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
