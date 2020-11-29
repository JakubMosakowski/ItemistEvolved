package com.jakmos.itemistevolved.presentation.main

import androidx.lifecycle.ViewModel
import com.jakmos.itemistevolved.presentation.base.lifecycle.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class MainModule {

  //region Binding

  @Binds
  @IntoMap
  @ViewModelKey(MainViewModel::class)
  abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

  //endregion
}
