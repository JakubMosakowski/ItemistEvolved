package com.jakmos.itemistevolved.application

import android.app.Application
import com.jakmos.itemistevolved.utility.BuildConfig.DEBUG
import com.jakmos.itemistevolved.utility.log.ILogger
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ItemistEvolved : Application() {

  //region Lifecycle

  override fun onCreate() {
    super.onCreate()

    // Initialize logger.
    initializeLogger()
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
