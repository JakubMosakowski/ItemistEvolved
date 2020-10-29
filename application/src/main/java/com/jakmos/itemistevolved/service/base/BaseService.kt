package com.jakmos.itemistevolved.service.base

import android.app.Service
import dagger.android.AndroidInjection

abstract class BaseService : Service() {

  //region Lifecycle

  override fun onCreate() {

    // Inject dependencies.
    AndroidInjection.inject(this)

    // Fallback to base class.
    super.onCreate()
  }

  //endregion
}
