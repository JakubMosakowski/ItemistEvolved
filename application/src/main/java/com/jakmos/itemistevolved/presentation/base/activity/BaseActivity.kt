package com.jakmos.itemistevolved.presentation.base.activity

import android.app.Activity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import co.windly.limbo.mvvm.activity.DaggerMvvmActivity
import co.windly.limbo.mvvm.trait.ActivityNavigationTrait
import co.windly.limbo.mvvm.trait.ActivityTrait
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.presentation.base.lifecycle.BaseViewModel

abstract class BaseActivity<Binding : ViewDataBinding, VM : BaseViewModel> :
  DaggerMvvmActivity<Binding, VM>(), ActivityTrait, ActivityNavigationTrait {

  //region Traits

  override val activityTrait: Activity
    get() = this

  override val navigationTrait: Activity
    get() = this

  //endregion

  //region Lifecycle

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Initialize navigation controller.
    initializeNavigationController()
  }

  //endregion

  //region Navigation Controller

  @get:IdRes
  val navFragment: Int = R.id.navFragment

  private var navigationController: NavController? = null

  private fun initializeNavigationController() {

    // Search for navigation host fragment.
    val host = supportFragmentManager
      .findFragmentById(R.id.navFragment) ?: return

    // Find nav controller.
    navigationController =
      (host as NavHostFragment).navController
  }

  //endregion
}
