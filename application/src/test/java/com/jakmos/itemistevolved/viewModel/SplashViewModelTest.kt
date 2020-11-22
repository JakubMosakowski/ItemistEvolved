package com.jakmos.itemistevolved.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.jakmos.itemistevolved.CoroutinesTestRule
import com.jakmos.itemistevolved.TestData.Companion.ANIMATION_CONFIG
import com.jakmos.itemistevolved.domain.manager.RemoteConfigDomainManager
import com.jakmos.itemistevolved.presentation.splash.SplashViewModel
import com.jakmos.itemistevolved.utility.livedata.observeForTesting
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner.StrictStubs


@ExperimentalCoroutinesApi
@RunWith(value = StrictStubs::class)
class SplashViewModelTest {

  //region ViewModel

  private lateinit var viewModel: SplashViewModel

  @Mock
  private lateinit var remoteConfigManager: RemoteConfigDomainManager

  //endregion

  //region Rules

  @get:Rule
  val rule = InstantTaskExecutorRule()

  @get:Rule
  val coroutinesRule = CoroutinesTestRule()

  //endregion

  //region Setup

  @Before
  fun setUp() {

    // Stub manager.
    Mockito
      .doReturn(ANIMATION_CONFIG)
      .`when`(remoteConfigManager).getSplashAnimationConfig()

    // Build view model.
    viewModel = SplashViewModel(remoteConfigManager)
  }

  //endregion

  //region Animation

  /**
   * Animation URL should be fetched from remote config on splash initialization.
   */
  @Test
  fun `Animation url requested`() = viewModel.animationConfig.observeForTesting {

    // Given.

    // When.
    viewModel.getAnimationUrl()

    // Then.
    runBlockingTest {
      Mockito
        .verify(remoteConfigManager)
        .init()
    }

    assertThat(viewModel.animationConfig.value)
      .isEqualTo(ANIMATION_CONFIG)
  }

  //endregion
}
