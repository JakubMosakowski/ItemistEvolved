package com.jakmos.itemistevolved.presentation.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import org.koin.androidx.viewmodel.ext.android.viewModel
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.AddFragmentBinding


class AddFragment : Fragment() {

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


        return binding.root
    }
}
