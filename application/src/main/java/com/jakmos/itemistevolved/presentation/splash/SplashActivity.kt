package com.jakmos.itemistevolved.presentation.splash

import android.animation.Animator
import android.os.Bundle
import androidx.activity.viewModels
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.presentation.base.activity.BaseActivity
import com.jakmos.itemistevolved.utility.vocabulary.NoOp
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

    // Observe animation url.
    observeAnimationUrl()
  }

  //endregion

  //region Navigation

  private fun observeNavigation() {

    viewModel
      .navigateToHome
      .observe(this) { navigateToMainView() }
  }

  //endregion

  //region Animation

  private fun observeAnimationUrl() {

    viewModel
      .animationUrl
      .observe(this) { setupAnimation(it.first, it.second) }
  }

  private fun setupLottieView() {

    // Add animation listener.
    splashAnimation
      .addAnimatorListener(this)

    // Set placeholder.
    splashAnimation.repeatCount = LottieDrawable.INFINITE
    splashAnimation.setAnimation(R.raw.splash)

    // Set fallback res.
    splashAnimation.setFailureListener {
      splashAnimation.repeatCount = 0
      splashAnimation.setAnimation(R.raw.splash)
    }

    // Get animation url.
    viewModel.getAnimationUrl()
  }

  /**
   * It adds new animation inside the listener because we don't want to stop the previous animation in the middle.
   */
  private fun setupAnimation(url: String, repeatCount: Int) {
    LottieCompositionFactory.fromUrl(this, url).addListener {
      splashAnimation.addAnimatorListener(getRepeatListener(it, repeatCount))
    }
  }

  private fun getRepeatListener(composition: LottieComposition,
    repeatCount: Int) = object : Animator.AnimatorListener {
    override fun onAnimationStart(animator: Animator?) = NoOp
    override fun onAnimationEnd(animator: Animator?) = NoOp
    override fun onAnimationCancel(animator: Animator?) = NoOp

    override fun onAnimationRepeat(animator: Animator?) {
      splashAnimation.pauseAnimation()
      splashAnimation.setComposition(composition)
      splashAnimation.repeatCount = repeatCount
      splashAnimation.progress = 0f
      splashAnimation.removeAnimatorListener(this)
      splashAnimation.playAnimation()
    }
  }

  override fun onAnimationEnd(animator: Animator?) = viewModel.animationEnded()

  //endregion
}
