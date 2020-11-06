package com.jakmos.itemistevolved.domain.model

import co.windly.limbo.utility.primitives.EMPTY
import co.windly.limbo.utility.primitives.ZERO
import com.jakmos.itemistevolved.utility.vocabulary.Id

data class Subsection(

  //region Id

  var id: Id = Id.ZERO,

  //endregion

  //region Text

  var text: String = String.EMPTY,

  //endregion

  //region Is Checked

  var isChecked: Boolean = false,

  //endregion

  //region Order Number

  var orderNumber: Long = Long.ZERO

  //endregion
)
