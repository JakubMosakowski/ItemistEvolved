package com.jakmos.itemistevolved.presentation.main.checklist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.FragmentChecklistBinding
import com.jakmos.itemistevolved.presentation.base.fragment.base.BaseFragment
import com.jakmos.itemistevolved.presentation.main.add.AddTrait

class ChecklistFragment : BaseFragment<FragmentChecklistBinding, ChecklistViewModel>(),
  AddTrait {

  //region Ui

  override val layoutRes: Int
    get() = R.layout.fragment_checklist

  //endregion

  //region View Model

  override val viewModel: ChecklistViewModel
    by viewModels { factory }

  //endregion

  //region Binding

  override fun bindView(binding: FragmentChecklistBinding) {
    binding
      .also { it.disposables = disposables }
      .also { it.viewModel = viewModel }
  }

  //endregion

  //region Lifecycle

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

  //endregion
}
