package com.jakmos.itemistevolved.utility.context

import androidx.fragment.app.Fragment
import io.reactivex.Observable

//region Network State

fun Fragment.observeInternetReachable(): Observable<Boolean> =
  requireContext()
    .observeInternetReachable()

//endregion
