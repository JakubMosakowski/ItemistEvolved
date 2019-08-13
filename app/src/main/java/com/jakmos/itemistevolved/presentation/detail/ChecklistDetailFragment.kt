package com.jakmos.itemistevolved.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ChecklistDetailFragment : BaseFragment() {

    private val args: ChecklistDetailFragmentArgs by navArgs()
    override val viewModel: ChecklistDetailViewModel by viewModel { parametersOf(args.checklist) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.checklist_detail_fragment, container, false)
    }

}
