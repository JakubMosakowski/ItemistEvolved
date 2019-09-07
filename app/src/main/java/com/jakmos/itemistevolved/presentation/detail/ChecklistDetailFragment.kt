package com.jakmos.itemistevolved.presentation.detail

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.ChecklistDetailFragmentBinding
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.presentation.base.BaseFragment
import com.jakmos.itemistevolved.presentation.commons.observe
import kotlinx.android.synthetic.main.checklist_detail_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ChecklistDetailFragment : BaseFragment() {
    private val args: ChecklistDetailFragmentArgs by navArgs()
    override val viewModel: ChecklistDetailViewModel by viewModel { parametersOf(args.checklist) }
    private val adapter by lazy {
        CheckboxAdapter(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ChecklistDetailFragmentBinding>(
            inflater, R.layout.checklist_detail_fragment, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        observe(viewModel.checklistLiveData, ::onChecklistsChange)

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun onChecklistsChange(checklist: Checklist?) {
        adapter.setData(checklist?.lines ?: emptyList())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
    }
}
