package com.jakmos.itemistevolved.application

import co.windly.limbo.dagger.scope.ApplicationScope
import com.jakmos.itemistevolved.domain.DomainComponent
import com.jakmos.itemistevolved.presentation.PresentationModule
import com.jakmos.itemistevolved.service.ServiceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@ApplicationScope
@Component(
  dependencies = [DomainComponent::class],
  modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class,
    PresentationModule::class,
    ServiceModule::class
  ])
interface ApplicationComponent : AndroidInjector<ItemistEvolved> {

  //region Component Builder

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun seedInstance(application: ItemistEvolved): Builder

    fun seedInstance(component: DomainComponent): Builder

    fun build(): ApplicationComponent
  }

  //endregion
}
