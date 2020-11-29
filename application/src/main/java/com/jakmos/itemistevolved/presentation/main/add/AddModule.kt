package com.jakmos.itemistevolved.presentation.main.add

import androidx.lifecycle.ViewModel
import com.jakmos.itemistevolved.presentation.base.lifecycle.ViewModelKey
import com.jakmos.itemistevolved.presentation.main.checklist.ChecklistViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class AddModule {

  //region Binding

  @Binds
  @IntoMap
  @ViewModelKey(AddViewModel::class)
  abstract fun bindAddViewModel(viewModel: AddViewModel): ViewModel

  //endregion
}
