package com.jakmos.itemistevolved.presentation.main.home

import androidx.lifecycle.ViewModel
import com.jakmos.itemistevolved.presentation.base.lifecycle.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class HomeModule {

  //region Binding

  @Binds
  @IntoMap
  @ViewModelKey(HomeViewModel::class)
  abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

  //endregion
}
