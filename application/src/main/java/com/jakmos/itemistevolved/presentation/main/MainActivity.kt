package com.jakmos.itemistevolved.presentation.main

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.presentation.base.activity.BaseActivity

class MainActivity : BaseActivity<MainViewModel>(), MainTrait {

  //region View Model

  override val viewModel: MainViewModel
    by viewModels { factory }

  //endregion

  //region Ui

  override val layoutRes: Int
    get() = R.layout.activity_main

  //endregion

  //region Traits

  override val contextTrait: Context
    get() = this

  //endregion
}
