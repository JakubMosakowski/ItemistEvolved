package com.jakmos.itemistevolved.presentation.main.checklist

import androidx.lifecycle.ViewModel
import com.jakmos.itemistevolved.presentation.base.lifecycle.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class ChecklistModule {

  //region Binding

  @Binds
  @IntoMap
  @ViewModelKey(ChecklistViewModel::class)
  abstract fun bindMainViewModel(viewModel: ChecklistViewModel): ViewModel

  //endregion
}
