package com.jakmos.itemistevolved.domain.manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.jakmos.itemistevolved.domain.mapper.ChecklistMapper
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.Subsection
import com.jakmos.itemistevolved.persistence.manager.ChecklistPersistenceManager
import com.jakmos.itemistevolved.utility.vocabulary.INVALID_ID
import com.jakmos.itemistevolved.utility.vocabulary.Id
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.joda.time.DateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChecklistDomainManager @Inject constructor(
  private val mapper: ChecklistMapper,
  private val persistence: ChecklistPersistenceManager
) {

  //region Observe - List

  fun observeChecklists(): LiveData<List<Checklist>> =
    persistence
      .observeChecklists()
      .map(mapper::mapViewListToDomainList)

  //endregion

  //region Observe - Checklist

  @ExperimentalCoroutinesApi
  fun observeChecklist(id: Id): Flow<Checklist> =
    persistence
      .observeChecklist(id)
      .map {
        mapper.mapViewToDomainSorted(it)
      }

  suspend fun getChecklist(id: Id): Checklist = withContext(Dispatchers.IO) {
    mapper.mapViewToDomainSorted(
      persistence
        .getChecklist(id)
    )
  }

  //endregion

  //region Insert

  private fun createChecklist(title: String, items: List<Subsection>): Checklist {
    val date = DateTime.now()

    return Checklist(
      name = title,
      imageUrl = "",
      createdAt = date,
      subsections = items
    )
  }

  suspend fun addChecklist(
    title: String,
    items: List<Subsection>,
    checklistId: Id = Id.INVALID_ID) = createChecklist(title, items).apply {

    /**
     * Set id to distinguish update from insert.
     */
    this.id = checklistId

    insertNewChecklist(this)
  }

  private suspend fun insertNewChecklist(checklist: Checklist) = withContext(Dispatchers.IO) {
    persistence
      .saveChecklist(mapper.mapDomainToEntity(checklist))
  }

  //endregion

  //region Remove

  suspend fun removeChecklist(checklist: Checklist) = withContext(Dispatchers.IO) {
    persistence
      .removeChecklist(mapper.mapDomainToEntity(checklist))
  }

  suspend fun removeChecklists(checklists: List<Checklist>) = withContext(Dispatchers.IO) {
    persistence
      .removeChecklists(mapper.mapDomainListToEntityList(checklists))
  }

  suspend fun clearChecklists() = withContext(Dispatchers.IO) {
    persistence
      .clearTable()
  }

  //endregion
}
