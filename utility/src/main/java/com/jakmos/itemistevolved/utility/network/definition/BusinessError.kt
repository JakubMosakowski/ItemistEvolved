package com.jakmos.itemistevolved.utility.network.definition

import androidx.annotation.StringDef

@StringDef(
  BusinessError.TODO
)
@Retention(AnnotationRetention.SOURCE)
annotation class BusinessError {

  companion object {

    const val TODO = "TODO"


  }
}
