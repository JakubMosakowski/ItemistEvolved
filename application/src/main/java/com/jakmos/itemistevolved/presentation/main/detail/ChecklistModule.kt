package com.jakmos.itemistevolved.presentation.main.detail

import androidx.lifecycle.ViewModel
import co.windly.limbo.dagger.ViewModelKey

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ChecklistModule {

  //region Binding

  @Binds
  @IntoMap
  @ViewModelKey(ChecklistViewModel::class)
  abstract fun bindMainViewModel(viewModel: ChecklistViewModel): ViewModel

  //endregion

  //region Contribution

  @ContributesAndroidInjector
  abstract fun contributeChecklistFragment(): ChecklistFragment

  //endregion
}
