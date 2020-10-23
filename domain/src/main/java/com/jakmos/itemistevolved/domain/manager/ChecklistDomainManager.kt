package com.jakmos.itemistevolved.domain.manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.jakmos.itemistevolved.domain.mapper.ChecklistMapper
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.persistence.manager.ChecklistPersistenceManager
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
}
