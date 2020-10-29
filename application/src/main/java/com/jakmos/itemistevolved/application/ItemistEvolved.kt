package com.jakmos.itemistevolved.application

import android.app.Application
import com.jakmos.itemistevolved.domain.DaggerDomainComponent
import com.jakmos.itemistevolved.utility.BuildConfig.DEBUG
import com.jakmos.itemistevolved.utility.log.ILogger
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class ItemistEvolved : Application(), HasAndroidInjector {

  //region Activity Injector

  @Inject
  lateinit var activityInjector: DispatchingAndroidInjector<Any>

  override fun androidInjector(): AndroidInjector<Any> =
    activityInjector

  //endregion

  //region Lifecycle

  override fun onCreate() {
    super.onCreate()

    // Initialize dependency injection.
    initializeDependencyInjection()

    // Initialize logger.
    initializeLogger()
  }

  //endregion

  //region Dependency Injection

  private fun initializeDependencyInjection() {

    // Initialize domain component.
    val domainComponent = DaggerDomainComponent.builder()
      .seedInstance(this)
      .seedInstance(resources)
      .build()

    // Initialize application component.
    DaggerApplicationComponent.builder()
      .seedInstance(this)
      .seedInstance(domainComponent)
      .build()

      // Inject dependencies.
      .also { it.inject(this) }
  }

  //endregion

  //region Logger

  private fun initializeLogger() {

    // Conditionally enable logger.
    ILogger
      .init(DEBUG)
  }

  //endregion
}
