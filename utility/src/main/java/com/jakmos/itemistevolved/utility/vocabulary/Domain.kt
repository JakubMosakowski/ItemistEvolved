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

val String.Companion.NONE: String
  get() = ""

//endregion

//region General - Long

val Long.Companion.ZERO: Long
  get() = 0L

//endregion

