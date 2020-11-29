package com.jakmos.itemistevolved.domain

import com.jakmos.itemistevolved.domain.manager.ChecklistDomainManager
import com.jakmos.itemistevolved.domain.manager.RemoteConfigDomainManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

  //region Share Managers

  @Provides
  fun checklistManager(manager: ChecklistDomainManager) = manager

  @Provides
  fun remoteConfigManager(manager: RemoteConfigDomainManager) = manager

  //endregion
}
