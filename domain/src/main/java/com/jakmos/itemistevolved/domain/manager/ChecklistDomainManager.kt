package com.jakmos.itemistevolved.domain.manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import co.windly.limbo.utility.primitives.orZero
import com.jakmos.itemistevolved.domain.mapper.ChecklistMapper
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.Subsection
import com.jakmos.itemistevolved.persistence.manager.ChecklistPersistenceManager
import com.jakmos.itemistevolved.utility.vocabulary.Id
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.joda.time.DateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChecklistDomainManager @Inject constructor(
  private val mapper: ChecklistMapper,
  private val persistence: ChecklistPersistenceManager
) {

  //region Common

  private fun sortSubsectionByOrder(list: List<Checklist>): List<Checklist> =
    list.map { checklist ->
      checklist.subsections = checklist.subsections.sortedBy { it.orderNumber }
      checklist
    }

  //endregion

  //region Observe

  fun observeChecklists(): LiveData<List<Checklist>> =
    persistence
      .observeChecklists()
      .map(mapper::mapViewListToDomainList)
      .map(::sortSubsectionByOrder)

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

  suspend fun addChecklist(title: String, items: List<Subsection>, checklistId: Id? = null) =
    createChecklist(title, items).apply {

      /**
       * Set id to distinguish update from insert.
       */
      this.id = checklistId.orZero()

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
