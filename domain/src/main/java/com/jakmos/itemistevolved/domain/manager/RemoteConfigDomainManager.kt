package com.jakmos.itemistevolved.domain.manager

import com.jakmos.itemistevolved.network.manager.RemoteConfigNetworkManager
import com.jakmos.itemistevolved.utility.network.remoteconfig.AnimationConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteConfigDomainManager @Inject constructor(
//  private val network: RemoteConfigNetworkManager
) {
  //TODO bring it back

  //region Init

  suspend fun init(): Boolean =
//    network.init()
true
  //endregion

  //region Splash Animation Config - Get

  fun getSplashAnimationConfig(): AnimationConfig =
    AnimationConfig("",1
//      network.getSplashAnimationUrl(),
//      network.getSplashAnimationRepeatCount().toInt()
    )

  //endregion
}
