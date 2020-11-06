package com.jakmos.itemistevolved.persistence.manager

import androidx.lifecycle.LiveData
import com.jakmos.itemistevolved.persistence.database.dao.ChecklistDao
import com.jakmos.itemistevolved.persistence.database.dao.SubsectionDao
import com.jakmos.itemistevolved.persistence.database.entity.ChecklistEntity
import com.jakmos.itemistevolved.persistence.database.entity.ChecklistView
import com.jakmos.itemistevolved.utility.vocabulary.Id
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChecklistPersistenceManager @Inject constructor(
  private val checklistDao: ChecklistDao,
  private val subsectionDao: SubsectionDao
) {

  //region Observe - List

  fun observeChecklists(): LiveData<List<ChecklistView>> =
    checklistDao
      .observeChecklists()

  //endregion

  //region Observe - Checklist

  @ExperimentalCoroutinesApi
  fun observeChecklist(id: Id): Flow<ChecklistView> =
    checklistDao
      .observeChecklist(id)
      .distinctUntilChanged()

  suspend fun getChecklist(id: Id): ChecklistView =
    checklistDao
      .getChecklist(id)

  //endregion

  //region Save

  suspend fun saveChecklist(checklist: ChecklistEntity) {

    // Cleanup.
    checklistDao.remove(checklist)
    subsectionDao.removeByChecklistId(checklist.id)

    // Insert checklist.
    val checklistId = checklistDao.insert(checklist)

    // Add id to subsections.
    var subsections = checklist.subsections.map {
      it.checklistId = checklistId
      it
    }

    // Add proper order to subsections.
    subsections = subsections.mapIndexed { index, entity ->
      entity.apply { orderNumber = index.toLong() }
    }

    // Insert subsections.
    subsectionDao.insert(subsections)
  }

  //endregion

  //region Update

  suspend fun updateSubsections(checklist: ChecklistEntity) {
    val subsections = checklist.subsections.map {
      it.checklistId = checklist.id
      it
    }

    subsectionDao
      .update(subsections)
  }

  //endregion

  //region Remove

  suspend fun removeChecklist(checklist: ChecklistEntity) =
    checklistDao
      .remove(checklist)

  suspend fun removeChecklists(checklists: List<ChecklistEntity>) =
    checklistDao
      .remove(checklists)

  suspend fun clearTable() =
    checklistDao
      .clearTable()

  //endregion
}
