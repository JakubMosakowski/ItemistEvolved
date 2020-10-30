package com.jakmos.itemistevolved.domain.model

import android.os.Parcelable
import co.windly.limbo.utility.primitives.EMPTY
import co.windly.limbo.utility.primitives.ZERO
import com.jakmos.itemistevolved.utility.vocabulary.Id
import kotlinx.android.parcel.Parcelize

@Parcelize
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

  //region Checklist Id

  var checklistId: Id = Id.ZERO

  //endregion
) : Parcelable
