package com.jakmos.itemistevolved.utility.definition

import androidx.annotation.StringDef
import com.jakmos.itemistevolved.utility.definition.RemoteConfigKey.Companion.SPLASH_ANIMATION_REPEAT_COUNT
import com.jakmos.itemistevolved.utility.definition.RemoteConfigKey.Companion.SPLASH_ANIMATION_URL
import kotlin.annotation.AnnotationRetention.SOURCE

@StringDef(
  SPLASH_ANIMATION_REPEAT_COUNT,
  SPLASH_ANIMATION_URL
)
@Retention(SOURCE)
annotation class RemoteConfigKey {

  companion object {

    const val SPLASH_ANIMATION_REPEAT_COUNT = "SPLASH_ANIMATION_REPEAT_COUNT"

    const val SPLASH_ANIMATION_URL = "SPLASH_ANIMATION_URL"

  }
}
