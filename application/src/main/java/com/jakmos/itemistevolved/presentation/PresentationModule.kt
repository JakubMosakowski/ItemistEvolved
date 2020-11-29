package com.jakmos.itemistevolved.presentation

import androidx.lifecycle.ViewModelProvider
import com.jakmos.itemistevolved.presentation.base.lifecycle.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PresentationModule {

  //region View Model Factory

  @Binds
  abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

  //endregion
}
