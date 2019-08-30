package com.jakmos.itemistevolved.presentation.add

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.AddFragmentBinding
import com.jakmos.itemistevolved.domain.model.Item
import com.jakmos.itemistevolved.domain.model.project.None
import com.jakmos.itemistevolved.domain.model.project.State
import com.jakmos.itemistevolved.presentation.add.adapter.ItemAdapter
import com.jakmos.itemistevolved.presentation.base.BaseFragment
import com.jakmos.itemistevolved.presentation.commons.observe
import kotlinx.android.synthetic.main.add_fragment.*
import org.koin.core.parameter.parametersOf
import timber.log.Timber


class AddFragment : BaseFragment() {

    private val args: AddFragmentArgs by navArgs()
    override val viewModel: AddViewModel by viewModel { parametersOf(args.checklist) }
    private val adapter by lazy {
        ItemAdapter(viewModel)
    }
    //TODO reorder items in list
    //TODO write tests for items in list

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
    }

    private fun setupRecyclerView() {
        itemsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        itemsRecyclerView.adapter = adapter
    }

    private fun onChecklistsChange(items: List<Item>?) {
        adapter.setData(items ?: emptyList())
    }

    private fun onStateChange(state: State<None>?) {
        when (state) {
            is State.Success -> changeScreen()
            is State.Error -> showError(state.cause)
        }
    }

    private fun changeScreen() {
        Timber.tag("KUBA").v("changeScreen ")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
    }
}
