package com.jakmos.itemistevolved.presentation.add

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.AddFragmentBinding
import com.jakmos.itemistevolved.domain.model.project.None
import com.jakmos.itemistevolved.domain.model.project.State
import com.jakmos.itemistevolved.presentation.base.BaseFragment
import com.jakmos.itemistevolved.presentation.commons.observe
import org.koin.core.parameter.parametersOf
import timber.log.Timber


class AddFragment : BaseFragment() {

    private val args: AddFragmentArgs by navArgs()
    override val viewModel: AddViewModel by viewModel { parametersOf(args.checklist) }

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
        observe(viewModel.state, ::onChecklistsStateChange)

        setHasOptionsMenu(false)
        return binding.root
    }

    private fun onChecklistsStateChange(state: State<None>?) {
        when (state) {
            is State.Success -> changeScreen()
            is State.Error -> showError(state.cause)
        }
    }

    private fun changeScreen() {
        Timber.tag("KUBA").v("changeScreen ")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
