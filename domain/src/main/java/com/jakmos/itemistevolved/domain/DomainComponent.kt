package com.jakmos.itemistevolved.domain

import android.content.Context
import android.content.res.Resources
import com.jakmos.itemistevolved.domain.manager.ChecklistDomainManager
import com.jakmos.itemistevolved.domain.manager.RemoteConfigDomainManager
import dagger.BindsInstance
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [DomainModule::class])
interface DomainComponent {

  //region Component Builder

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun seedInstance(context: Context): Builder

    @BindsInstance
    fun seedInstance(resources: Resources): Builder

    fun build(): DomainComponent
  }

  //endregion

  //region Share Managers

  fun checklistManager(): ChecklistDomainManager

  fun remoteConfigManager(): RemoteConfigDomainManager

  //endregion
}
