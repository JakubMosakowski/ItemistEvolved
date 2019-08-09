package com.jakmos.itemistevolved.presentation.checklists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.ChecklistsFragmentBinding
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.presentation.checklists.ChecklistsViewModel.ChecklistsState
import com.jakmos.itemistevolved.presentation.commons.observe
import timber.log.Timber


class ChecklistsFragment : Fragment() {

    private val viewModel: ChecklistsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<ChecklistsFragmentBinding>(
            inflater, R.layout.checklists_fragment, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        observe(viewModel.state, ::onChecklistsStateChange)

        return binding.root
    }

    private fun onChecklistsStateChange(state: ChecklistsState?) {
        when (state) {
            is ChecklistsState.Loading -> showLoading()
            is ChecklistsState.Empty -> showEmptyState()
            is ChecklistsState.Success -> renderList(state.checklists)
            is ChecklistsState.Error -> showError(state.error)
        }
    }

    private fun showError(error: Exception) {
        Timber.tag("KUBA").v("showError $error")
    }

    private fun renderList(checklists: List<Checklist>) {
        Timber.tag("KUBA").v("renderList $checklists")
    }

    private fun showEmptyState() {
        Timber.tag("KUBA").v("showEmptyState ")
    }

    private fun showLoading() {
        Timber.tag("KUBA").v("showLoading ")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadData()
    }
}
