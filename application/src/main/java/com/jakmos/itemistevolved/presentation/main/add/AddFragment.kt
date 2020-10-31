package com.jakmos.itemistevolved.presentation.main.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import co.windly.limbo.recyclerview.addSpaceDecoration
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.FragmentAddBinding
import com.jakmos.itemistevolved.presentation.base.fragment.base.BaseFragment
import com.jakmos.itemistevolved.presentation.main.add.item.ClickSubsectionDeleteEventHook
import com.jakmos.itemistevolved.presentation.main.add.item.SimpleSubsection
import com.jakmos.itemistevolved.presentation.main.add.item.SubsectionItem
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import kotlinx.android.synthetic.main.fragment_add.subsectionsRv


class AddFragment : BaseFragment<FragmentAddBinding, AddViewModel>(),
  AddTrait {

  //region Ui

  override val layoutRes: Int
    get() = R.layout.fragment_add

  //endregion

  //region View Model

  override val viewModel: AddViewModel
    by viewModels { factory }

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

    // Observe submit clicked.
    observeSubmitClicked()

    // Initialize subsection recycler view.
    initializeSubsectionRecyclerView()

    // Observe subsections.
    observeSubsections()

    // Initialize event hooks.
    initializeEventHooks()
  }

  //endregion

  //region Submit

  private fun observeSubmitClicked() {

    // Observe submit clicked.
    viewModel
      .submitClicked
      .observe(viewLifecycleOwner) { navigateToHomeView() }
  }

  //endregion

  //region Subsection

  private fun List<SimpleSubsection>.subsectionItems(): List<SubsectionItem> =
    map(::SubsectionItem)

  private val subsectionAdapter: GenericFastItemAdapter
    by lazy { GenericFastItemAdapter() }

  private fun showSubsectionItems(result: List<SimpleSubsection>) {

    // Show list of subsection.
    subsectionAdapter.setNewList(result.subsectionItems())
  }

  private fun observeSubsections() {

    // Observe result.
    viewModel
      .subsections
      .observe(viewLifecycleOwner, ::showSubsectionItems)
  }

  //endregion

  //region Recycler View

  private fun initializeSubsectionRecyclerView() = with(subsectionsRv) {

    // TODO add items dragging.

    // TODO add exit fragment callback

    // TODO add view model tests

    // TODO Support edit checklist

    // TODO add keyboard showing on fragment open

    // TODO add subsection after keyboard "enter" is clicked while focused on add item to list edit text

    // TODO Move to add item to list edit text after keyboard "enter" is clicked while focused on checklist title edit text

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

  //region Event Hook

  private fun initializeEventHooks() {

    // Prepare subsection event hooks.
    val subsectionDeleteEventHook = ClickSubsectionDeleteEventHook(viewModel::onDeleteClicked)

    // Initialize event hooks.
    subsectionAdapter.addEventHook(subsectionDeleteEventHook)
  }

  //endregion
}
