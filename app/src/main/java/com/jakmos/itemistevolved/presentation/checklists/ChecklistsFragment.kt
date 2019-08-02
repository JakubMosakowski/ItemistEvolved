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


        return binding.root
    }
}
