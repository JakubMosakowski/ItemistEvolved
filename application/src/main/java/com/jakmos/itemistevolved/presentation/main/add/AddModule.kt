package com.jakmos.itemistevolved.presentation.main.add

import androidx.lifecycle.ViewModel
import co.windly.limbo.dagger.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AddModule {

  //region Binding

  @Binds
  @IntoMap
  @ViewModelKey(AddViewModel::class)
  abstract fun bindAddViewModel(viewModel: AddViewModel): ViewModel

  //endregion

  //region Contribution

  @ContributesAndroidInjector
  abstract fun contributeAddFragment(): AddFragment

  //endregion
}
