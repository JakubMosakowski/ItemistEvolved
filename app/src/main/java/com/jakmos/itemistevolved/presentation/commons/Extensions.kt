package com.jakmos.itemistevolved.presentation.commons

import android.view.Menu
import androidx.core.view.iterator
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}

fun Menu.setMenuVisibility(isVisible: Boolean) {
    for (item in iterator()) {
        item.isVisible = isVisible
    }
}