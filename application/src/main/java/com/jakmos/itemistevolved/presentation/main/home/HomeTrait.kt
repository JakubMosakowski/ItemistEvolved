package com.jakmos.itemistevolved.presentation.main.home

import co.windly.limbo.mvvm.trait.FragmentNavigationTrait
import com.jakmos.itemistevolved.presentation.base.trait.ConfirmExitTrait

interface HomeTrait : HomeNavigationTrait,
  ConfirmExitTrait

interface HomeNavigationTrait : FragmentNavigationTrait {


}
