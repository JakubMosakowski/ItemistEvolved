package com.jakmos.itemistevolved.presentation.main.checklist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.FragmentChecklistBinding
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.presentation.base.fragment.back.BackFragment
import com.jakmos.itemistevolved.presentation.main.add.AddTrait
import com.jakmos.itemistevolved.utility.log.ILogger

class ChecklistFragment : BackFragment<FragmentChecklistBinding, ChecklistViewModel>(),
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
      .also { it.viewModel = viewModel }
  }

  //endregion

  //region Arguments

  private val args: ChecklistFragmentArgs
    by navArgs()

  //endregion

  //region Lifecycle

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    //TODO pass here only checklist id -> fetch it from db.
    //TODO MAYBE -> write test for observing db, or smth else that is going to be added
    //TODO add clear button
    //TODO add recycler view

    // Log argument.
    ILogger.v("Checklist: ${args.checklist}.")

    // Consume arguments.
    viewModel.onChecklistAvailable(args.checklist)

    // Observe checklist update.
    observeChecklistUpdate()
  }

  //endregion

  //region Checklist

  private fun observeChecklistUpdate() {
    viewModel
      .checklist
      .observe(viewLifecycleOwner, ::handleChecklistUpdate)
  }

  private fun handleChecklistUpdate(checklist: Checklist) {

    // Inform view model about update.
    viewModel.onChecklistUpdated()
  }

  //endregion
}
