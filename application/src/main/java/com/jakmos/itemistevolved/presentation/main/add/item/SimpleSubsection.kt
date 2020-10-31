package com.jakmos.itemistevolved.presentation.main.add.item

import co.windly.limbo.utility.primitives.EMPTY
import co.windly.limbo.utility.primitives.ZERO
import com.jakmos.itemistevolved.utility.vocabulary.Id

data class SimpleSubsection(

  //region Id

  var id: Id = Id.ZERO,

  //endregion

  //region Text

  var text: String = String.EMPTY

  //endregion
)
