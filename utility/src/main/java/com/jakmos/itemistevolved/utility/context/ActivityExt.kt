package com.jakmos.itemistevolved.utility.context

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

//region Soft Keyboard

/**
 * Hides soft input keyboard.
 */
fun Activity.hideSoftInput() {

  // Get top-level decor view.
  val view = this.window.decorView

  // Do nothing if no context found.
  view.context ?: return

  // Get access to the input method manager.
  val manager =
    view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

  // Hide keyboard.
  manager.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * Shows soft input keyboard.
 */
fun Activity.showSoftInput(focusedView: View) {

  // Request focus.
  focusedView.requestFocus()

  // Get access to the input method manager.
  val manager =
    focusedView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

  // Show keyboard.
  manager.showSoftInput(focusedView, 0)
}

//endregion
