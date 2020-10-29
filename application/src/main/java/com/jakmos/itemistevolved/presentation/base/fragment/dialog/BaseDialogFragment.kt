package com.jakmos.itemistevolved.presentation.base.fragment.dialog

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import co.windly.limbo.mvvm.fragment.DaggerMvvmDialogFragment
import com.jakmos.itemistevolved.presentation.base.lifecycle.BaseViewModel
import com.jakmos.itemistevolved.presentation.base.trait.SnackbarTrait

abstract class BaseDialogFragment<Binding : ViewDataBinding, VM : BaseViewModel> :
  DaggerMvvmDialogFragment<Binding, VM>(), SnackbarTrait {

  //region Traits

  override var snackbarTrait: View? = null

  //endregion

  //region Lifecycle

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    // Search for snackbar trait view.
    snackbarTrait =
      parentFragment?.view?.findViewById(snackbarTraitViewId)
  }

  //endregion
}
