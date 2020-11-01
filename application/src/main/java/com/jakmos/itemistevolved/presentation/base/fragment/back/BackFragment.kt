package com.jakmos.itemistevolved.presentation.base.fragment.back

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.appbar.MaterialToolbar
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.presentation.base.fragment.base.BaseFragment
import com.jakmos.itemistevolved.presentation.base.lifecycle.BaseViewModel


abstract class BackFragment<Binding : ViewDataBinding, VM : BaseViewModel> : BaseFragment<Binding, VM>() {

  //region Ui

  @get:IdRes
  protected open val toolbarViewId: Int
    get() = R.id.toolbarView

  //endregion

  //region Lifecycle

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    // Find toolbar.
    val toolbar =
      view.findViewById<MaterialToolbar>(toolbarViewId)

    // Initialize toolbar.
    initializeToolbar(toolbar)
  }

  //endregion

  //region Toolbar

  @get:DrawableRes
  protected open val navigationIconResId: Int
    get() = R.drawable.ic_arrow_back

  open fun initializeToolbar(toolbar: MaterialToolbar) {

    // Setup with nav controller.
    NavigationUI.setupWithNavController(toolbar, findNavController())

    // https://issuetracker.google.com/issues/121078028
    findNavController().addOnDestinationChangedListener { _, _, _ ->

      // Update navigation icon.
      toolbar.setNavigationIcon(navigationIconResId)
    }
  }

  //endregion
}
