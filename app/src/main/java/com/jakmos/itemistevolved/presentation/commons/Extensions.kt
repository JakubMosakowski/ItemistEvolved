package com.jakmos.itemistevolved.presentation.commons

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.jakmos.itemistevolved.R

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}

fun ViewGroup.inflate(
    layoutId: Int, attachToRoot: Boolean = false
): View = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun dp2px(dp: Int): Float = dp * Resources.getSystem().displayMetrics.density

fun Context.loadForegroundDrawable(): Drawable? {
    var foregroundDrawable: Drawable? = null
    val attrs = intArrayOf(R.attr.selectableItemBackground)
    val typedArray: TypedArray = this.obtainStyledAttributes(attrs)
    try {
        val backgroundResource = typedArray.getResourceId(0, 0)
        foregroundDrawable = ContextCompat.getDrawable(this, backgroundResource)
    } finally {
        typedArray.recycle()
        return foregroundDrawable
    }
}