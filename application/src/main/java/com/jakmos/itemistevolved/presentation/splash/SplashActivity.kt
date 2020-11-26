package com.jakmos.itemistevolved.presentation.splash

import android.os.Bundle
import androidx.activity.viewModels
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.ActivitySplashBinding
import com.jakmos.itemistevolved.presentation.base.activity.BaseActivity
import com.jakmos.itemistevolved.utility.context.addEndAnimationListener
import com.jakmos.itemistevolved.utility.context.getOneTimeRepeatListener
import com.jakmos.itemistevolved.utility.context.getSmoothFailureListener
import com.jakmos.itemistevolved.utility.context.setPlaceholder
import com.jakmos.itemistevolved.utility.network.remoteconfig.AnimationConfig

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashTrait {

  //region Ui

  override val layoutResId: Int
    get() = R.layout.activity_splash

  //endregion

  //region View Model

  override val viewModel: SplashViewModel
    by viewModels { factory }

  //endregion

  //region Binding

  override fun bindView(binding: ActivitySplashBinding) {
    splashAnimation = binding.splashAnimation
  }

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

  private lateinit var splashAnimation: LottieAnimationView

  private fun observeAnimationConfig() {

    viewModel
      .animationConfig
      .observe(this, ::setupAnimationConfig)
  }

  private fun setupLottieView() {

    // Add animation listener.
    splashAnimation
      .addEndAnimationListener(viewModel::animationEnded)

    // Set placeholder.
    splashAnimation.setPlaceholder(R.raw.splash)

    // Set failure listener.
    splashAnimation.setFailureListener(getSplashFailureListener())

    // Get animation url.
    viewModel.getAnimationUrl()
  }

  //endregion

  //region Animation

  private fun setupAnimationConfig(animationConfig: AnimationConfig) {
    LottieCompositionFactory.fromUrl(this, animationConfig.url)

      // Add success listener.
      .addListener {
        val repeatListener = getSplashRepeatListener(it, animationConfig.repeatCount)
        splashAnimation.addAnimatorListener(repeatListener)
      }

      // Add failure listener.
      .addFailureListener(getSplashFailureListener())
  }

  private fun getSplashRepeatListener(
    composition: LottieComposition,
    repeatCount: Int
  ) = splashAnimation.getOneTimeRepeatListener {
    splashAnimation.pauseAnimation()
    splashAnimation.setComposition(composition)
    splashAnimation.repeatCount = repeatCount
    splashAnimation.progress = 0f
    splashAnimation.playAnimation()
  }

  private fun getSplashFailureListener() = splashAnimation.getSmoothFailureListener {
    splashAnimation.repeatCount = 0
    splashAnimation.playAnimation()
  }

  //endregion
}
