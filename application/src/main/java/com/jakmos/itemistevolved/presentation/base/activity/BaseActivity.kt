package com.jakmos.itemistevolved.presentation.base.activity

import android.app.Activity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import co.windly.limbo.mvvm.trait.ActivityNavigationTrait
import co.windly.limbo.mvvm.trait.ActivityTrait
import com.jakmos.itemistevolved.R
import dagger.android.AndroidInjection
import com.jakmos.itemistevolved.presentation.base.lifecycle.BaseViewModel
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity(), ActivityTrait,
  ActivityNavigationTrait {

  //region Ui

  @get:LayoutRes
  abstract val layoutRes: Int

  //endregion

  //region View Model

  @Inject
  lateinit var factory: ViewModelProvider.Factory

  abstract val viewModel: VM

  //endregion

  //region Traits

  override val activityTrait: Activity
    get() = this

  override val navigationTrait: Activity
    get() = this

  //endregion

  //region Lifecycle

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Set content view.
    setContentView(layoutRes)

    // Initialize navigation controller.
    initializeNavigationController()

    // Inject dependencies.
    AndroidInjection.inject(this)
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
