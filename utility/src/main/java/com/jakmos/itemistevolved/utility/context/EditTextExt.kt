package com.jakmos.itemistevolved.utility.context

import android.view.inputmethod.EditorInfo
import android.widget.EditText

//region Editor Action

fun EditText.clearFocusWhenDone() {

  // Invoke editor action listener.
  setOnEditorActionListener { _, actionId, _ ->

    // Clear focus from edit text on done action.
    if (actionId == EditorInfo.IME_ACTION_DONE) {
      clearFocus()
    }
    false
  }
}

fun EditText.doActionOnDone(action: () -> Unit) {

  // Invoke editor action listener.
  setOnEditorActionListener { _, actionId, _ ->

    // Trigger action on done clicked.
    if (actionId == EditorInfo.IME_ACTION_DONE) {
      action.invoke()
    }
    false
  }
}

//endregion
