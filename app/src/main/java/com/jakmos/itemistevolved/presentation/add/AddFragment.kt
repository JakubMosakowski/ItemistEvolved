package com.jakmos.itemistevolved.presentation.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import org.koin.androidx.viewmodel.ext.android.viewModel
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.AddFragmentBinding
import com.jakmos.itemistevolved.domain.model.project.None
import com.jakmos.itemistevolved.domain.model.project.State
import com.jakmos.itemistevolved.presentation.base.BaseFragment
import com.jakmos.itemistevolved.presentation.commons.observe
import timber.log.Timber


class AddFragment : BaseFragment() {

    private val viewModel: AddViewModel by viewModel()

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
}
