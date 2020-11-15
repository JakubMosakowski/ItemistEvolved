package com.jakmos.itemistevolved.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.windly.limbo.mvvm.lifecycle.SingleLiveEvent
import com.jakmos.itemistevolved.domain.manager.RemoteConfigDomainManager
import com.jakmos.itemistevolved.presentation.base.lifecycle.BaseViewModel
import com.jakmos.itemistevolved.utility.network.remoteconfig.AnimationConfig
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
  private val remoteConfigManager: RemoteConfigDomainManager
) : BaseViewModel() {

  // TODO add tests

  //region Home

  private val _navigateToHome: SingleLiveEvent<Any> =
    SingleLiveEvent()

  internal val navigateToHome: MutableLiveData<Any> =
    _navigateToHome

  //endregion

  //region Splash

  private val _animationConfig: MutableLiveData<AnimationConfig> =
    MutableLiveData()

  internal val animationConfig: LiveData<AnimationConfig> =
    _animationConfig

  fun animationEnded() = _navigateToHome.post()

  fun getAnimationUrl() = viewModelScope.launch {

    // Initialize remote config.
    remoteConfigManager.init()

    // Get splash config.
    val splashAnimationConfig = remoteConfigManager.getSplashAnimationConfig()
    _animationConfig.postValue(splashAnimationConfig)
  }

  //endregion
}
