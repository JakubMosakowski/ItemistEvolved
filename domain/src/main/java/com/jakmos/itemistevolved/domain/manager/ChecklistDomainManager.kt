package com.jakmos.itemistevolved.domain.manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.jakmos.itemistevolved.domain.mapper.ChecklistMapper
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.Subsection
import com.jakmos.itemistevolved.persistence.manager.ChecklistPersistenceManager
import org.joda.time.DateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChecklistDomainManager @Inject constructor(
  private val mapper: ChecklistMapper,
  private val persistence: ChecklistPersistenceManager
) {

  //region Observe

  fun observeChecklists(): LiveData<List<Checklist>> =
    persistence
      .observeChecklists()
      .map(mapper::mapViewListToDomainList)

  fun observeChecklistsByName(name: String): LiveData<List<Checklist>> =
    persistence
      .observeChecklistsByName(name)
      .map(mapper::mapViewListToDomainList)

  //endregion

  //region Insert

  private fun createChecklist(title: String, items: List<String>): Checklist {
    val date = DateTime.now()
    val subsections = items.map {
      Subsection(text = it)
    }

    return Checklist(
      name = title,
      imageUrl = "",
      createdAt = date,
      updatedAt = date,
      subsections = subsections
    )
  }

  suspend fun addChecklist(title: String, items: List<String>) {
    val checklist = createChecklist(title, items)

    persistence
      .saveChecklist(mapper.mapDomainToEntity(checklist))
  }

  //endregion

  //region Remove

  suspend fun removeChecklist(checklist: Checklist) =
    persistence
      .removeChecklist(mapper.mapDomainToEntity(checklist))

  suspend fun removeChecklists(checklists: List<Checklist>) =
    persistence
      .removeChecklists(mapper.mapDomainListToEntityList(checklists))

  suspend fun clearChecklists() =
    persistence
      .clearTable()

  //endregion
}
