package com.jakmos.itemistevolved.presentation.add

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.AddFragmentBinding
import com.jakmos.itemistevolved.domain.model.Item
import com.jakmos.itemistevolved.domain.model.project.None
import com.jakmos.itemistevolved.domain.model.project.State
import com.jakmos.itemistevolved.presentation.base.BaseFragment
import com.jakmos.itemistevolved.presentation.commons.callback.DragAndDropCallback
import com.jakmos.itemistevolved.presentation.commons.observe
import kotlinx.android.synthetic.main.add_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class AddFragment : BaseFragment(), ItemAdapter.ItemAdapterListener {
    private val args: AddFragmentArgs by navArgs()
    override val viewModel: AddViewModel by viewModel { parametersOf(args.checklist) }
    private val adapter by lazy {
        ItemAdapter(this)
    }
    private val itemTouchHelper by lazy {
        ItemTouchHelper(DragAndDropCallback(viewModel))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<AddFragmentBinding>(
            inflater, R.layout.add_fragment, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        observe(viewModel.state, ::onStateChange)
        observe(viewModel.items, ::onChecklistsChange)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        titleEditText.requestFocus()
    }

    private fun setupRecyclerView() {
        itemsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        itemsRecyclerView.adapter = adapter
        itemTouchHelper.attachToRecyclerView(itemsRecyclerView)
    }

    private fun onChecklistsChange(items: List<Item>?) {
        val fromTo = viewModel.draggedFromTo
        if (fromTo == AddViewModel.IS_NOT_DRAGGING) {
            adapter.setData(items ?: emptyList())
            return
        }

        viewModel.draggedFromTo = AddViewModel.IS_NOT_DRAGGING
        adapter.notifyItemMoved(fromTo.first, fromTo.second)
    }

    private fun onStateChange(state: State<None>?) {
        when (state) {
            is State.Error -> showError(state.cause)
        }
    }

    override fun startDragging(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    override fun onDeleteClicked(model: Item) {
        viewModel.onDeleteClicked(model)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
    }
}
