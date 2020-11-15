package com.jakmos.itemistevolved.presentation.main.checklist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import co.windly.limbo.recyclerview.addDividerDecorationWithHorizontalInsets
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.FragmentChecklistBinding
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.Subsection
import com.jakmos.itemistevolved.presentation.base.fragment.back.BackFragment
import com.jakmos.itemistevolved.presentation.main.add.AddTrait
import com.jakmos.itemistevolved.presentation.main.checklist.item.CheckboxItem
import com.jakmos.itemistevolved.presentation.main.checklist.item.ClickCheckboxEventHook
import com.jakmos.itemistevolved.utility.log.ILogger
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import kotlinx.android.synthetic.main.fragment_checklist.checkboxRv
import kotlinx.coroutines.ExperimentalCoroutinesApi

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

  @ExperimentalCoroutinesApi
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    // Log argument.
    ILogger.v("Checklist: ${args.checklistId}.")

    // Consume arguments.
    viewModel.onChecklistAvailable(args.checklistId)

    // Observe checklist update.
    observeChecklistUpdate()

    // Initialize recycler view.
    initializeRecyclerView()

    // Initialize event hooks.
    initializeEventHooks()
  }

  //endregion

  //region Checklist

  private fun List<Subsection>.subsectionItems(): List<CheckboxItem> =
    map(::CheckboxItem)

  private val subsectionAdapter: GenericFastItemAdapter
    by lazy { GenericFastItemAdapter() }

  private fun observeChecklistUpdate() {
    viewModel
      .checklist
      .observe(viewLifecycleOwner, ::handleChecklistUpdate)
  }

  private fun handleChecklistUpdate(checklist: Checklist) {

    // Inform view model about update.
    viewModel.onChecklistUpdated()

    // Show list of subsections.
    subsectionAdapter.setNewList(checklist.subsections.subsectionItems())
  }

  //endregion

  //region Recycler View

  private fun initializeRecyclerView() = with(checkboxRv) {

    // Add item decoration.
    addDividerDecorationWithHorizontalInsets(
      dividerRes = R.drawable.divider_checkbox,
      leftInsetRes = R.dimen.size_p16,
      rightInsetRes = R.dimen.size_p16
    )

    // Assign adapter to recycler view.
    adapter = subsectionAdapter
  }

  //endregion

  //region Event Hook

  private fun initializeEventHooks() {

    // Prepare checkbox event hook.
    val eventHook = ClickCheckboxEventHook(viewModel::onSubsectionClicked)

    // Initialize event hooks.
    subsectionAdapter
      .addEventHook(eventHook)
  }

  //endregion
}
