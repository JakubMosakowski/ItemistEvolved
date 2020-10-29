package com.jakmos.itemistevolved.presentation.main

import androidx.lifecycle.ViewModel
import co.windly.limbo.dagger.ViewModelKey
import com.jakmos.itemistevolved.presentation.main.add.AddModule
import com.jakmos.itemistevolved.presentation.main.detail.ChecklistModule
import com.jakmos.itemistevolved.presentation.main.home.HomeModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [
  AddModule::class,
  ChecklistModule::class,
  HomeModule::class
])
abstract class MainModule {

  //region Binding

  @Binds
  @IntoMap
  @ViewModelKey(MainViewModel::class)
  abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

  //endregion

  //region Contribution

  @ContributesAndroidInjector
  abstract fun contributeMainActivity(): MainActivity

  //endregion
}
