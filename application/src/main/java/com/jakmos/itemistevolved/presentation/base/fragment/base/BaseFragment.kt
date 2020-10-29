package com.jakmos.itemistevolved.presentation.base.fragment.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import co.windly.limbo.mvvm.fragment.DaggerMvvmFragment
import com.jakmos.itemistevolved.presentation.base.lifecycle.BaseViewModel
import com.jakmos.itemistevolved.presentation.base.trait.SnackbarTrait

abstract class BaseFragment<Binding : ViewDataBinding, VM : BaseViewModel> :
  DaggerMvvmFragment<Binding, VM>(), SnackbarTrait {

  //region Traits

  override var snackbarTrait: View? = null

  //endregion

  //region Lifecycle

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    // Search for snackbar trait view.
    snackbarTrait =
      view.findViewById(snackbarTraitViewId)
  }

  //endregion
}
