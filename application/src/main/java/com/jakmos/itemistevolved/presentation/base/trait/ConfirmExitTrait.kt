package com.jakmos.itemistevolved.presentation.base.trait

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import co.windly.limbo.mvvm.trait.FragmentNavigationTrait
import com.afollestad.materialdialogs.MaterialDialog
import com.jakmos.itemistevolved.R

interface ConfirmExitTrait : FragmentNavigationTrait {

  //region Trait

  val activityNavigationTrait: FragmentActivity

  //endregion

  //region Activity

  private fun finishAffinity() {
    activityNavigationTrait
      .finishAffinity()
  }

  //endregion

  //region Callback

  val confirmExitTrait: ConfirmExitCallback

  fun confirmExitTemplate(onConfirmClicked: () -> Unit = {}): ConfirmExitCallback =
    ConfirmExitCallback(this, onConfirmClicked)

  fun registerConfirmExitCallback() {

    // Register callback in traits activity.
    activityNavigationTrait
      .onBackPressedDispatcher
      .addCallback(confirmExitTrait)
  }

  fun unregisterConfirmExitCallback() {
    confirmExitTrait.remove()
  }

  class ConfirmExitCallback(
    private val trait: ConfirmExitTrait,
    private val onConfirmClicked: () -> Unit
  ) : OnBackPressedCallback(true) {

    override fun handleOnBackPressed() {

      // Show exit confirmation dialog.
      trait.showConfirmCloseDialog(onConfirmClicked)
    }
  }

  //endregion

  //region Dialog

  private fun showConfirmCloseDialog(onConfirmClicked: () -> Unit) {

    // Show material dialog to ask user for confirmation.
    MaterialDialog(fragmentTrait.requireContext()).show {

      // Configure title.
      title(R.string.confirmation)

      // Configure message.
      message(R.string.are_you_sure_you_want_to_quit)

      // Configure negative button.
      negativeButton(R.string.cancel)

      // Configure positive button.
      positiveButton(R.string.ok) {

        // Do additional action on confirm click.
        onConfirmClicked.invoke()

        // Finish activity.
        finishAffinity()
      }
    }
  }

  //endregion
}
