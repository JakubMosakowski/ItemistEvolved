package com.jakmos.itemistevolved.presentation.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import co.windly.limbo.recyclerview.addSpaceDecoration
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.FragmentHomeBinding
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.presentation.base.fragment.base.BaseFragment
import com.jakmos.itemistevolved.presentation.base.trait.ConfirmExitTrait.ConfirmExitCallback
import com.jakmos.itemistevolved.presentation.main.home.item.ChecklistItem
import com.jakmos.itemistevolved.presentation.main.home.item.ClickChecklistDeleteEventHook
import com.jakmos.itemistevolved.presentation.main.home.item.ClickChecklistEditEventHook
import com.jakmos.itemistevolved.presentation.main.home.item.ClickChecklistEventHook
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import kotlinx.android.synthetic.main.fragment_home.checklistsRv

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
  HomeTrait {

  //region Trait

  override val activityNavigationTrait: FragmentActivity
    get() = requireActivity()

  //endregion

  //region Ui

  override val layoutRes: Int
    get() = R.layout.fragment_home

  //endregion

  //region View Model

  override val viewModel: HomeViewModel
    by viewModels { factory }

  //endregion

  //region Traits

  override val confirmExitTrait: ConfirmExitCallback
    by lazy { confirmExitTemplate() }

  //endregion

  //region Binding

  override fun bindView(binding: FragmentHomeBinding) {
    binding
      .also { it.viewModel = viewModel }
  }

  //endregion

  //region Lifecycle

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    // Observe add clicked.
    observeAddClicked()

    // Observe delete clicked.
    observeDeleteClicked()

    // Initialize checklist recycler view.
    initializeChecklistRecyclerView()

    // Observe checklists.
    observeChecklists()

    // Initialize event hooks.
    initializeEventHooks()
  }

  override fun onPause() {
    super.onPause()

    // Unregister back pressed callback.
    unregisterConfirmExitCallback()
  }

  override fun onResume() {
    super.onResume()

    // Register back pressed callback.
    registerConfirmExitCallback()
  }

  //endregion

  //region Checklist

  private fun List<Checklist>.checklistItems(): List<ChecklistItem> =
    map(::ChecklistItem)

  private val checklistAdapter: GenericFastItemAdapter
    by lazy { GenericFastItemAdapter() }

  private fun showChecklistItems(result: List<Checklist>) {

    // Show list of checklist.
    checklistAdapter.setNewList(result.checklistItems())
  }

  private fun observeChecklists() {

    // Observe result.
    viewModel
      .checklists
      .observe(viewLifecycleOwner, ::showChecklistItems)
  }

  //endregion

  //region Recycler View

  private fun initializeChecklistRecyclerView() = with(checklistsRv) {

    // Add space decorator.
    addSpaceDecoration(
      leftResId = R.dimen.size_none,
      topResId = R.dimen.size_p4,
      rightResId = R.dimen.size_none,
      bottomResId = R.dimen.size_p4
    )

    // Assign adapter to recycler view.
    adapter = checklistAdapter
  }

  //endregion

  //region Add

  private fun observeAddClicked() {

    // Observe add clicked.
    viewModel
      .addClicked
      .observe(viewLifecycleOwner) { navigateToAddView() }
  }

  //endregion

  //region Delete

  private fun observeDeleteClicked() {

    // Observe delete clicked.
    viewModel
      .deleteSnackbarRequested
      .observe(viewLifecycleOwner) { handleSnackbarRequested() }
  }

  private fun handleSnackbarRequested() {

    // Show snackbar.
    showRemoveSnackbar(
      viewModel::onUndoClicked,
      viewModel::onSnackbarDismiss
    )
  }

  //endregion

  //region Event Hook

  private fun initializeEventHooks() {

    // Prepare checklist event hooks.
    val checklistEventHook = ClickChecklistEventHook(::navigateToChecklistView)
    val checklistEditEventHook = ClickChecklistEditEventHook(::navigateToEditView)
    val checklistDeleteEventHook = ClickChecklistDeleteEventHook(viewModel::onDeleteClicked)

    // Initialize event hooks.
    checklistAdapter
      .addEventHooks(listOf(
        checklistEventHook,
        checklistEditEventHook,
        checklistDeleteEventHook
      ))
  }

  //endregion
}

