package com.jakmos.itemistevolved.presentation.main

import android.content.Context
import androidx.activity.viewModels
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.ActivityMainBinding
import com.jakmos.itemistevolved.presentation.base.activity.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainTrait {

  //region View Model

  override val viewModel: MainViewModel
    by viewModels { factory }

  //endregion

  //region Ui

  override val layoutResId: Int
    get() = R.layout.activity_main

  //endregion

  //region Binding

  override fun bindView(binding: ActivityMainBinding) {

  }

  //endregion

  //region Traits

  override val contextTrait: Context
    get() = this

  //endregion
}
