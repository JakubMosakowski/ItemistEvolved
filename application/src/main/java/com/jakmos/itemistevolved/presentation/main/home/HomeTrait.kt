package com.jakmos.itemistevolved.presentation.main.home

import androidx.navigation.fragment.findNavController
import co.windly.limbo.mvvm.trait.FragmentNavigationTrait
import com.jakmos.itemistevolved.NavGraphDirections.Companion.actionAdd
import com.jakmos.itemistevolved.NavGraphDirections.Companion.actionChecklist
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.presentation.base.trait.ConfirmExitTrait

interface HomeTrait : HomeNavigationTrait,
  ConfirmExitTrait

interface HomeNavigationTrait : FragmentNavigationTrait {

  fun navigateToChecklistView(checklist: Checklist) {

    // Navigate to checklist view.
    navigationTrait
      .findNavController()
      .navigate(actionChecklist(checklist))
  }

  fun navigateToEditView(checklist: Checklist) {

    // Navigate to edit view.
    navigationTrait
      .findNavController()
      .navigate(actionAdd(checklist))
  }

  fun navigateToAddView() {

    // Navigate to add view.
    navigationTrait
      .findNavController()
      .navigate(actionAdd())
  }

}
