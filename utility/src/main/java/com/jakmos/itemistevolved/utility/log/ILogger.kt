package com.jakmos.itemistevolved.utility.log

import timber.log.Timber

object ILogger {

  //endregion

  //region Initialization

  fun init(enable: Boolean) {
    if (enable) {
      Timber.plant(Timber.DebugTree())
    }
  }

  //endregion

  //region Verbose

  fun v(message: String, vararg objects: Any) {
    Timber.v(message, *objects)
  }

  fun v(throwable: Throwable, message: String, vararg objects: Any) {
    Timber.v(throwable, message, *objects)
  }

  fun v(error: Throwable) {
    Timber.v(error)
  }

  //endregion

  //region Error

  fun e(message: String, vararg objects: Any) {
    Timber.e(message, *objects)
  }

  fun e(throwable: Throwable, message: String, vararg objects: Any) {
    Timber.e(throwable, message, *objects)
  }

  fun e(error: Throwable) {
    Timber.e(error)
  }

  //endregion
}
