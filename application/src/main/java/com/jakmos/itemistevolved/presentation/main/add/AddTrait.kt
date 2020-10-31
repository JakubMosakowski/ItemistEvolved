package com.jakmos.itemistevolved.presentation.main.add

import androidx.navigation.fragment.findNavController
import co.windly.limbo.mvvm.trait.FragmentNavigationTrait
import com.jakmos.itemistevolved.NavGraphDirections

//region Add

interface AddTrait :
  AddNavigationTrait

//endregion

//region Add - Navigation

interface AddNavigationTrait : FragmentNavigationTrait {

  fun navigateToHomeView() {

    // Navigate to home view.
    navigationTrait
      .findNavController()
      .navigate(NavGraphDirections.actionHome())
  }

}

//endregion
