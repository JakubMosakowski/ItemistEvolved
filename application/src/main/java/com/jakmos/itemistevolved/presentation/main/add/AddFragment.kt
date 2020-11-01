package com.jakmos.itemistevolved.presentation.main.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import co.windly.limbo.recyclerview.addSpaceDecoration
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.FragmentAddBinding
import com.jakmos.itemistevolved.domain.model.Subsection
import com.jakmos.itemistevolved.presentation.base.fragment.back.BackFragment
import com.jakmos.itemistevolved.presentation.common.recyclerview.TranslucentDragCallback
import com.jakmos.itemistevolved.presentation.main.add.item.ClickSubsectionDeleteEventHook
import com.jakmos.itemistevolved.presentation.main.add.item.SubsectionItem
import com.jakmos.itemistevolved.utility.context.doActionOnDone
import com.jakmos.itemistevolved.utility.context.showSoftInput
import com.jakmos.itemistevolved.utility.log.ILogger
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.mikepenz.fastadapter.drag.ItemTouchCallback
import com.mikepenz.fastadapter.select.getSelectExtension
import com.mikepenz.fastadapter.utils.DragDropUtil
import kotlinx.android.synthetic.main.fragment_add.lineEditText
import kotlinx.android.synthetic.main.fragment_add.subsectionsRv
import kotlinx.android.synthetic.main.fragment_add.titleEditText


class AddFragment : BackFragment<FragmentAddBinding, AddViewModel>(),
  AddTrait {

  //region Ui

  override val layoutRes: Int
    get() = R.layout.fragment_add

  //endregion

  //region View Model

  override val viewModel: AddViewModel
    by viewModels { factory }

  //endregion

  //region Arguments

  private val args: AddFragmentArgs
    by navArgs()

  //endregion

  //region Binding

  override fun bindView(binding: FragmentAddBinding) {
    binding
      .also { it.disposables = disposables }
      .also { it.viewModel = viewModel }
  }

  //endregion

  //region Lifecycle

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    // Show keyboard.
    activity?.showSoftInput(titleEditText)

    // Log argument.
    ILogger.v("checklist: ${args.checklist}.")

    // Consume arguments.
    viewModel.onChecklistAvailable(args.checklist)

    // Observe submit completed.
    observeSubmitCompleted()

    // Observe subsectionsOrderRequested.
    observeSubsectionsOrderRequested()

    // Initialize subsection recycler view.
    initializeSubsectionRecyclerView()

    // Observe subsections.
    observeSubsections()

    // Do action when done will be triggered.
    doActionOnAddDone()

    // Initialize event hooks.
    initializeEventHooks()
  }

  //endregion

  //region Submit

  private fun observeSubmitCompleted() {

    // Observe submit completed.
    viewModel
      .submitCompleted
      .observe(viewLifecycleOwner) { navigateToHomeView() }
  }

  //endregion

  //region Subsection

  private fun List<Subsection>.subsectionItems(): List<SubsectionItem> =
    map {
      SubsectionItem(
        it,
        touchHelper
      )
    }

  private val subsectionAdapter: FastItemAdapter<SubsectionItem>
    by lazy { FastItemAdapter<SubsectionItem>() }

  private fun showSubsectionItems(result: List<Subsection>) {

    // Show list of subsection.
    subsectionAdapter.setNewList(result.subsectionItems())
  }

  private fun observeSubsections() {

    // Observe result.
    viewModel
      .subsections
      .observe(viewLifecycleOwner, ::showSubsectionItems)
  }

  private fun doActionOnAddDone() = with(lineEditText) {

    // Do action when click on keyboard done.
    doActionOnDone(viewModel::onAddClicked)
  }

  private fun observeSubsectionsOrderRequested() {

    // Observe subsections order requested.
    viewModel
      .currentSubsectionsOrderRequested
      .observe(viewLifecycleOwner) { handleSubsectionOrderRequested() }
  }

  private fun handleSubsectionOrderRequested() {

    val currentSubsectionsOrder = subsectionAdapter.adapterItems.map { it.model }

    viewModel.postNewSubsectionOrder(currentSubsectionsOrder)
  }

  //endregion

  //region Recycler View

  private fun initializeSubsectionRecyclerView() = with(subsectionsRv) {

    // TODO add view model tests

    // Setup drag and drop.
    touchHelper.attachToRecyclerView(subsectionsRv)
    subsectionAdapter.getSelectExtension().apply {
      isSelectable = true
    }

    // Add space decorator.
    addSpaceDecoration(
      leftResId = R.dimen.size_none,
      topResId = R.dimen.size_p4,
      rightResId = R.dimen.size_none,
      bottomResId = R.dimen.size_p4
    )

    // Assign adapter to recycler view.
    adapter = subsectionAdapter
  }

  //endregion

  //region Drag

  private val itemTouchCallback = object : ItemTouchCallback {

    override fun itemTouchOnMove(oldPosition: Int, newPosition: Int): Boolean {
      DragDropUtil.onMove(subsectionAdapter.itemAdapter, oldPosition, newPosition)
      return true
    }
  }

  private val touchHelper: ItemTouchHelper
    by lazy {
      val touchCallback = TranslucentDragCallback(itemTouchCallback)
      ItemTouchHelper(touchCallback)
    }

  //endregion

  //region Event Hook

  private fun initializeEventHooks() {

    // Prepare subsection event hooks.
    val subsectionDeleteEventHook = ClickSubsectionDeleteEventHook(viewModel::onDeleteClicked)

    // Initialize event hooks.
    subsectionAdapter.addEventHook(subsectionDeleteEventHook)
  }

  //endregion
}
