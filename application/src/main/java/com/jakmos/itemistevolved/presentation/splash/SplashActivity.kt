package com.jakmos.itemistevolved.presentation.splash

import android.os.Bundle
import androidx.activity.viewModels
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.presentation.base.activity.BaseActivity
import com.jakmos.itemistevolved.utility.context.addEndAnimationListener
import com.jakmos.itemistevolved.utility.context.getOneTimeRepeatListener
import com.jakmos.itemistevolved.utility.context.getSmoothFailureListener
import com.jakmos.itemistevolved.utility.network.remoteconfig.AnimationConfig
import kotlinx.android.synthetic.main.activity_splash.splashAnimation

class SplashActivity : BaseActivity<SplashViewModel>(), SplashTrait {

  //region Ui

  override val layoutRes: Int
    get() = R.layout.activity_splash

  //endregion

  //region View Model

  override val viewModel: SplashViewModel
    by viewModels { factory }

  //endregion

  //region Lifecycle

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Observe navigation.
    observeNavigation()

    // Setup animation.
    setupLottieView()

    // Observe animation config.
    observeAnimationConfig()
  }

  //endregion

  //region Navigation

  private fun observeNavigation() {

    viewModel
      .navigateToHome
      .observe(this) { navigateToMainView() }
  }

  //endregion

  //region Lottie View

  private fun observeAnimationConfig() {

    viewModel
      .animationConfig
      .observe(this, ::setupAnimation)
  }

  private fun setupLottieView() {

    // Add animation listener.
    splashAnimation
      .addEndAnimationListener(viewModel::animationEnded)

    // Set placeholder.
    splashAnimation.repeatCount = LottieDrawable.INFINITE
    splashAnimation.setAnimation(R.raw.splash)

    // Set fallback res.
    splashAnimation.setFailureListener(getFailureListener())

    // Get animation url.
    viewModel.getAnimationUrl()
  }

  //endregion

  //region Animation

  /**
   * It adds new animation inside the listener because we don't want to stop the previous animation in the middle.
   */
  private fun setupAnimation(animationConfig: AnimationConfig) {
    LottieCompositionFactory.fromUrl(this, animationConfig.url)
      .addListener {

        val repeatListener = splashAnimation.getOneTimeRepeatListener {
          splashAnimation.pauseAnimation()
          splashAnimation.setComposition(it)
          splashAnimation.repeatCount = animationConfig.repeatCount
          splashAnimation.progress = 0f
          splashAnimation.playAnimation()
        }

        splashAnimation.addAnimatorListener(repeatListener)
      }
      .addFailureListener(getFailureListener())
  }

  private fun getFailureListener() = splashAnimation.getSmoothFailureListener {
    splashAnimation.repeatCount = 0
    splashAnimation.playAnimation()
  }

  //endregion
}
