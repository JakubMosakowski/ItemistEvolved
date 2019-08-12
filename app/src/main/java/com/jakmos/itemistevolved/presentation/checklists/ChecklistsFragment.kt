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
import com.jakmos.itemistevolved.presentation.checklists.adapter.ChecklistAdapter
import com.jakmos.itemistevolved.presentation.commons.observe
import kotlinx.android.synthetic.main.checklists_fragment.*
import timber.log.Timber
import androidx.core.content.ContextCompat
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.presentation.base.BaseFragment
import com.jakmos.itemistevolved.presentation.commons.adapter.BottomItemDecoration


class ChecklistsFragment : ChecklistAdapter.ChecklistAdapterListener, BaseFragment() {

    private val viewModel: ChecklistsViewModel by viewModel()
    private val adapter = ChecklistAdapter(this)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let {
            val dividerItemDecoration = BottomItemDecoration(it)
            checklistsRv.addItemDecoration(dividerItemDecoration)
        }

        checklistsRv.layoutManager = LinearLayoutManager(requireContext())
        checklistsRv.adapter = adapter
    }

    private fun onChecklistsStateChange(state: State<List<Checklist>>?) {
        when (state) {
            is State.Success -> renderList(state.data)
            is State.Error -> showError(state.cause)
        }
    }

    private fun renderList(checklists: List<Checklist>) {
        adapter.setData(checklists)
    }

    override fun onItemClicked(model: Checklist) {
        Timber.tag("KUBA").v("onItemClicked $model")
    }

    override fun onEditClicked(model: Checklist) {
        Timber.tag("KUBA").v("onEditClicked $model")
    }
}
