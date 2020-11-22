package com.jakmos.itemistevolved.splash

import androidx.test.rule.ActivityTestRule
import com.jakmos.itemistevolved.R.id
import com.jakmos.itemistevolved.presentation.splash.SplashActivity
import com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasAnyDrawable
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep
import org.junit.Rule
import org.junit.Test

class SplashActivityTest {

  //region Rules

  @get:Rule
  var activityRule = ActivityTestRule(SplashActivity::class.java)

  //endregion

  //region Animation

  /**
   * Default animation should be shown on app initialization.
   */
  @Test
  fun showDefaultAnimationOnStart() {

    // Given.

    // When.
    // App starts

    // Then.
    sleep(2000)
    assertHasAnyDrawable(id.splashAnimation)
  }

  /**
   * Default animation should be shown if app is offline.
   */
  @Test
  fun showDefaultAnimationWhileOffline() {
    //TODO device offline
  }

  /**
   * Different animation should be loaded if app is online.
   */
  @Test
  fun showDefaultAnimationWhileOnline() {
    //TODO device online
  }

  //endregion

  //region Navigation

  /**
   * Splash activity should be closed after few seconds.
   */
  @Test
  fun closeActivityAfterFewSeconds() {

    // Given.

    // When.
    //App starts

    // Then.
    assertDisplayed(id.splashAnimation)

    // When.
    sleep(7000)

    // Then.
    assertNotExist(id.splashAnimation)
  }

  //endregion
}
