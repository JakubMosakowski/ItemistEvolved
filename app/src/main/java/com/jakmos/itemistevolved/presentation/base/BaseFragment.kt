package com.jakmos.itemistevolved.presentation.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.domain.model.project.EventObserver
import timber.log.Timber
import java.lang.Exception

abstract class BaseFragment : Fragment() {

    protected abstract val viewModel: BaseViewModel

    private var alertDialog: AlertDialog? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.navigationCommands.observe(this, EventObserver {
            navigate(it)
        })
        viewModel.keyboardCommands.observe(this, EventObserver {
            handleKeyboard(it)
        })
    }

    private fun handleKeyboard(command: BaseViewModel.KeyboardCommand) {
        when (command) {
            is BaseViewModel.KeyboardCommand.Show -> showKeyboard()
            else -> hideKeyboard()
        }
    }

    private fun navigate(command: BaseViewModel.NavigationCommand) {
        when (command) {
            is BaseViewModel.NavigationCommand.To ->
                findNavController().navigate(command.directions)
            is BaseViewModel.NavigationCommand.Back ->
                findNavController().popBackStack()
            is BaseViewModel.NavigationCommand.BackTo ->
                findNavController().popBackStack()
        }
    }

    fun showError(
        positiveAction: () -> Unit
    ) {
        if (null == activity) return

        val context = activity as? Context? ?: return

        val title = context.getString(R.string.oh_no)
        val message = context.getString(R.string.something_went_wrong)
        val positiveButtonTitle = context.getString(R.string.retry)
        val negativeButtonTitle = context.getString(R.string.exit)

        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        setPositiveAction(builder, positiveAction, positiveButtonTitle)
        setNegativeAction(builder, { activity?.onBackPressed() }, negativeButtonTitle)

        builder.setCancelable(false)

        alertDialog = builder.show()
    }

    private fun setPositiveAction(
        builder: AlertDialog.Builder,
        positiveAction: () -> Unit,
        buttonTitle: String
    ) {
        builder.setPositiveButton(buttonTitle) { _, _ ->
            try {
                positiveAction.invoke()
            } catch (e: Exception) {
                Timber.tag("KUBA").e("setPositiveAction $e")
            }
        }
    }

    private fun setNegativeAction(
        builder: AlertDialog.Builder,
        negativeAction: () -> Unit,
        buttonTitle: String
    ) {
        builder.setNegativeButton(buttonTitle) { _, _ ->
            try {
                negativeAction.invoke()
            } catch (e: Exception) {
                Timber.tag("KUBA").e("setNegativeAction ")
            }
        }
    }

    private fun showKeyboard() {
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)

        this.activity?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        )
    }
}
