package com.jakmos.itemistevolved.utility.context

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.callbacks.onDismiss

fun MaterialDialog.dismissBottomSheet(onDismiss: () -> Unit) {

  // Perform callback action in case if bottom sheet is not showing.
  if (!isShowing) {
    onDismiss().also { return }
  }

  // Hide bottom sheet and then perform callback action.
  onDismiss { onDismiss() }
    .also { it.dismiss() }
}
