package com.jakmos.itemistevolved.presentation.main.home

import androidx.lifecycle.ViewModel
import co.windly.limbo.dagger.ViewModelKey

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class HomeModule {

  //region Binding

  @Binds
  @IntoMap
  @ViewModelKey(HomeViewModel::class)
  abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

  //endregion

  //region Contribution

  @ContributesAndroidInjector
  abstract fun contributeHomeFragment(): HomeFragment

  //endregion
}
