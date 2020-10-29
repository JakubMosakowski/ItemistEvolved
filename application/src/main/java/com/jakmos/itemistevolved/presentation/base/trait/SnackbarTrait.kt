package com.jakmos.itemistevolved.presentation.base.trait

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import com.jakmos.itemistevolved.R

interface SnackbarTrait {

  //region View

  var snackbarTrait: View?

  val snackbarTraitViewId: Int
    get() = R.id.rootView

  //endregion

  //region Common

  fun showErrorMessage(message: String) =
    showLongSnackbar(message)

  fun showTodoMessage() =
    showLongSnackbar(R.string.todo)

  fun showTryAgainErrorMessage() =
    showLongSnackbar(R.string.an_error_occurred_try_again_later)

  //endregion

  //region Long

  fun showLongSnackbar(text: CharSequence) {

    // Do nothing if there is no message view.
    snackbarTrait ?: return

    // Show long message.
    Snackbar
      .make(requireNotNull(snackbarTrait), text, Snackbar.LENGTH_LONG)
      .show()
  }

  fun showLongSnackbar(@StringRes resId: Int) {

    // Do nothing if there is no message view.
    snackbarTrait ?: return

    // Show long message.
    Snackbar
      .make(requireNotNull(snackbarTrait), resId, Snackbar.LENGTH_LONG)
      .show()
  }

  fun showLongSnackbar(@StringRes resId: Int, vararg args: String) {

    // Do nothing if there is no message view.
    snackbarTrait ?: return

    // Show long message.
    Snackbar
      .make(
        requireNotNull(snackbarTrait),
        requireNotNull(snackbarTrait).context.getString(resId, *args),
        Snackbar.LENGTH_LONG
      )
      .show()
  }

  //endregion

  //region Short

  fun showShortSnackbar(text: CharSequence) {

    // Do nothing if there is no message view.
    snackbarTrait ?: return

    // Show short message.
    Snackbar
      .make(requireNotNull(snackbarTrait), text, Snackbar.LENGTH_SHORT)
      .show()
  }

  fun showShortSnackbar(@StringRes resId: Int) {

    // Do nothing if there is no message view.
    snackbarTrait ?: return

    // Show short message.
    Snackbar
      .make(requireNotNull(snackbarTrait), resId, Snackbar.LENGTH_SHORT)
      .show()
  }

  fun showShortSnackbar(@StringRes resId: Int, vararg args: String) {

    // Do nothing if there is no message view.
    snackbarTrait ?: return

    // Show short message.
    Snackbar
      .make(
        requireNotNull(snackbarTrait),
        requireNotNull(snackbarTrait).context.getString(resId, *args),
        Snackbar.LENGTH_SHORT
      )
      .show()
  }

  //endregion
}
