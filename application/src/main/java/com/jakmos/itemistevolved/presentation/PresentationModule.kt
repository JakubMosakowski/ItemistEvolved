package com.jakmos.itemistevolved.presentation

import androidx.lifecycle.ViewModelProvider
import co.windly.limbo.dagger.lifecycle.ViewModelFactory
import com.jakmos.itemistevolved.presentation.common.CommonModule
import com.jakmos.itemistevolved.presentation.main.MainModule
import com.jakmos.itemistevolved.presentation.splash.SplashModule
import dagger.Binds
import dagger.Module

@Module(includes = [
  CommonModule::class,
  MainModule::class,
  SplashModule::class
])
abstract class PresentationModule {

  //region View Model Factory

  @Binds
  abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

  //endregion
}
