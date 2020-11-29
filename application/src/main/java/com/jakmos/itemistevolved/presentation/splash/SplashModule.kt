package com.jakmos.itemistevolved.presentation.splash

import androidx.lifecycle.ViewModel
import com.jakmos.itemistevolved.presentation.base.lifecycle.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class SplashModule {

  //region Binding

  @Binds
  @IntoMap
  @ViewModelKey(SplashViewModel::class)
  abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

  //endregion
}
