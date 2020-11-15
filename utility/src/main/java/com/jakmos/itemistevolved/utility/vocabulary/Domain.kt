package com.jakmos.itemistevolved.utility.vocabulary

//region Api

typealias Authenticated = Boolean

typealias UserId = Long

//endregion

//region Domain

typealias Id = Long

typealias Username = String

typealias Email = String

typealias Password = String

//endregion

//region General - String

fun String?.longOrNull() =
  if (isNullOrEmpty()) null else this!!.toLong()

fun String?.stringOrNull() =
  if (isNullOrEmpty()) null else this

//endregion

//region General - Id

val Long.Companion.INVALID_ID: Id
  get() = -1L

//endregion

//region No-op

typealias NoOp = Unit

//endregion

