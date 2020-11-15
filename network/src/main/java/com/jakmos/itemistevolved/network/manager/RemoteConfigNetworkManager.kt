package com.jakmos.itemistevolved.network.manager

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.jakmos.itemistevolved.utility.definition.RemoteConfigKey
import io.reactivex.annotations.SchedulerSupport
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@SchedulerSupport(value = SchedulerSupport.IO)
class RemoteConfigNetworkManager @Inject constructor(
  private val remoteConfig: FirebaseRemoteConfig
) {

  //region Init

  suspend fun init(): Boolean =
    remoteConfig
      .fetchAndActivate().await()

  //endregion

  //region Get Splash Animation Config

  fun getSplashAnimationUrl(): String =
    remoteConfig
      .getString(RemoteConfigKey.SPLASH_ANIMATION_URL)

  fun getSplashAnimationRepeatCount(): Long =
    remoteConfig
      .getLong(RemoteConfigKey.SPLASH_ANIMATION_REPEAT_COUNT)

  //endregion
}
