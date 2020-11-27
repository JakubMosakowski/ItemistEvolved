package com.jakmos.itemistevolved.utility.vocabulary

//region Domain

typealias Id = Long

//endregion

//region General - Id

val Long.Companion.INVALID_ID: Id
  get() = -1L

//endregion

//region No-op

typealias NoOp = Unit

//endregion

