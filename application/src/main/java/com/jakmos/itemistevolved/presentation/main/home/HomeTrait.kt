package com.jakmos.itemistevolved.presentation.main.home

import androidx.navigation.fragment.findNavController
import co.windly.limbo.mvvm.trait.FragmentNavigationTrait
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.jakmos.itemistevolved.NavGraphDirections.Companion.actionAdd
import com.jakmos.itemistevolved.NavGraphDirections.Companion.actionChecklist
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.presentation.base.trait.ConfirmExitTrait
import com.jakmos.itemistevolved.presentation.base.trait.SnackbarTrait
import com.jakmos.itemistevolved.utility.vocabulary.Id

//region Home

interface HomeTrait :
  HomeNavigationTrait,
  HomeSnackbarTrait,
  ConfirmExitTrait

//endregion

//region Home - Navigation

interface HomeNavigationTrait : FragmentNavigationTrait {

  fun navigateToChecklistView(checklistId: Id) {

    // Navigate to checklist view.
    navigationTrait
      .findNavController()
      .navigate(actionChecklist(checklistId))
  }

  fun navigateToEditView(checklistId: Id) {

    // Navigate to edit view.
    navigationTrait
      .findNavController()
      .navigate(actionAdd(checklistId))
  }

  fun navigateToAddView() {

    // Navigate to add view.
    navigationTrait
      .findNavController()
      .navigate(actionAdd())
  }

}

//endregion

//region Home - Snackbar

interface HomeSnackbarTrait : SnackbarTrait {

  fun showRemoveSnackbar(undoAction: () -> Unit, dismissedAction: () -> Unit) {
    Snackbar
      .make(requireNotNull(snackbarTrait), R.string.home_bring_back_checklist_question,
        Snackbar.LENGTH_LONG)
      .setAction(R.string.home_undo) { undoAction.invoke() }
      .addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {

          // Invoke dismiss action only after enough time pass.
          if (event == DISMISS_EVENT_TIMEOUT)
            dismissedAction.invoke()
          super.onDismissed(transientBottomBar, event)
        }
      })
      .show()
  }

}

//endregion
