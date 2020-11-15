package com.jakmos.itemistevolved.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.windly.limbo.mvvm.lifecycle.SingleLiveEvent
import com.jakmos.itemistevolved.presentation.base.lifecycle.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class SplashViewModel @Inject constructor(
) : BaseViewModel() {

  //region Home

  private val _navigateToHome: SingleLiveEvent<Any> =
    SingleLiveEvent()

  internal val navigateToHome: MutableLiveData<Any> =
    _navigateToHome

  //endregion

  //region Splash

  private val _animationUrl: MutableLiveData<Pair<String, Int>> =
    MutableLiveData()

  internal val animationUrl: LiveData<Pair<String, Int>> =
    _animationUrl

  fun animationEnded() = _navigateToHome.post()

  fun getAnimationUrl() =
    viewModelScope.launch {

      // To simulate fetching url from remote.
      delay(2000)
      _animationUrl.postValue(
        if (Random.nextBoolean())
          Pair("https://assets9.lottiefiles.com/private_files/lf30_wGOUY8.json", 2)
        else
          Pair("https://assets6.lottiefiles.com/packages/lf20_iax08muv.json", 0)
      )
    }

  //endregion
}
