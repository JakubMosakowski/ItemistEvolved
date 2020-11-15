package com.jakmos.itemistevolved.presentation.splash

import android.content.Intent
import co.windly.limbo.mvvm.trait.ActivityNavigationTrait
import com.jakmos.itemistevolved.presentation.main.MainActivity

//region Splash

interface SplashTrait :
  SplashNavigationTrait

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
