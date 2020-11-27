package com.jakmos.itemistevolved.presentation.binding

import android.view.View
import androidx.databinding.BindingAdapter
import co.windly.limbo.utility.view.changeVisibility
import com.google.android.material.textfield.TextInputLayout

//region View

@BindingAdapter(
  requireAll = false,
  value = ["visible", "occupying"]
)
fun setVisibility(view: View, visible: Boolean, occupying: Boolean = false) {

  // Load resource based on occupying state.
  val res = when (occupying) {
    true -> View.INVISIBLE
    false -> View.GONE
  }

  // Update view visibility.
  view.changeVisibility(visible, res)
}

//endregion
