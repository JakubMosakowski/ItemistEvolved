package com.jakmos.itemistevolved.presentation.splash

import android.animation.Animator
import android.content.Intent
import co.windly.limbo.mvvm.trait.ActivityNavigationTrait
import com.jakmos.itemistevolved.presentation.main.MainActivity
import com.jakmos.itemistevolved.utility.vocabulary.NoOp

//region Splash

interface SplashTrait :
  SplashAnimationTrait,
  SplashNavigationTrait

//endregion

//region Splash - Animation

interface SplashAnimationTrait : Animator.AnimatorListener {

  override fun onAnimationCancel(animator: Animator?) = NoOp

  override fun onAnimationRepeat(animator: Animator?) = NoOp

  override fun onAnimationStart(animator: Animator?) = NoOp

}

//endregion

//region Splash - Navigation

interface SplashNavigationTrait : ActivityNavigationTrait {

  fun navigateToMainView() {

    // Navigate to main view.
    Intent(navigationTrait, MainActivity::class.java)
      .also { navigationTrait.startActivity(it) }

    // Close caller activity.
    navigationTrait.finish()
  }

}

//endregion
