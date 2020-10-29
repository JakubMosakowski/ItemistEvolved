package com.jakmos.itemistevolved.presentation.main

import co.windly.limbo.mvvm.trait.ActivityNavigationTrait
import co.windly.limbo.mvvm.trait.ContextTrait


//region Main

interface MainTrait :
  ContextTrait,
  MainNavigationTrait

//endregion

//region Main -  Navigation

interface MainNavigationTrait : ActivityNavigationTrait {


}

//endregion
