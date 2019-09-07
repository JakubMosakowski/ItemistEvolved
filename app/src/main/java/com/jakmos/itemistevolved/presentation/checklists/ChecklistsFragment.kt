package com.jakmos.itemistevolved.presentation.checklists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.ChecklistsFragmentBinding
import com.jakmos.itemistevolved.domain.model.project.State
import com.jakmos.itemistevolved.presentation.commons.observe
import kotlinx.android.synthetic.main.checklists_fragment.*
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.project.None
import com.jakmos.itemistevolved.presentation.base.BaseFragment
import com.jakmos.itemistevolved.presentation.commons.adapter.BottomItemDecoration


class ChecklistsFragment : ChecklistAdapter.ChecklistAdapterListener, BaseFragment() {

    override val viewModel: ChecklistsViewModel by viewModel()
    private val adapter = ChecklistAdapter(this)
    private val snackbarListener = View.OnClickListener {
        viewModel.onUndoClicked()
    }

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
        observe(viewModel.checklists, ::onChecklistsChange)

        return binding.root
    }

    private fun onChecklistsChange(list: List<Checklist>?) {
        adapter.setData(list ?: emptyList())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.loadData()
    }

    private fun setupRecyclerView() {
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let {
            val dividerItemDecoration = BottomItemDecoration(it)
            checklistsRv.addItemDecoration(dividerItemDecoration)
        }

        checklistsRv.layoutManager = LinearLayoutManager(requireContext())
        checklistsRv.adapter = adapter
    }

    private fun onChecklistsStateChange(state: State<None>?) {
        when (state) {
            is State.Error -> showError(state.cause)
            is State.Removing -> showSnackbar()
        }
    }

    private fun showSnackbar() {
        Snackbar.make(view ?: return, R.string.checklist_removed_undo, Snackbar.LENGTH_LONG)
            .setAction(R.string.undo, snackbarListener)
            .addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    if (event == DISMISS_EVENT_TIMEOUT)
                        viewModel.snackbarDismissed()
                    super.onDismissed(transientBottomBar, event)
                }
            })
            .show()
    }

    override fun onItemClicked(model: Checklist) {
        viewModel.onItemClicked(model)
    }

    override fun onEditClicked(model: Checklist) {
        viewModel.onEditClicked(model)
    }

    override fun onDeleteClicked(model: Checklist) {
        viewModel.onDeleteClicked(model)
    }
}

