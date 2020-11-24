package com.jakmos.itemistevolved.splash

import androidx.test.ext.junit.rules.activityScenarioRule
import com.jakmos.itemistevolved.R.id
import com.jakmos.itemistevolved.RetryTestRule
import com.jakmos.itemistevolved.presentation.splash.SplashActivity
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

class SplashActivityTest {

  //region Rules

  @get:Rule
  val rule: RuleChain = RuleChain
    .outerRule(RetryTestRule(3))
    .around(activityScenarioRule<SplashActivity>())

  //endregion

  //region Navigation

  /**
   * Splash activity should be closed after few seconds.
   */
  @Test
  fun closeActivityAfterFewSeconds() {

    // Given.

    // When.
    sleep(7000)

    // Then.
    assertNotExist(id.splashAnimation)
  }

  //endregion
}
