package com.jakmos.itemistevolved.presentation.base.trait

import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.utility.log.ILogger
import com.jakmos.itemistevolved.utility.network.definition.BusinessError

//region Error

interface ErrorTrait : SnackbarTrait {

  fun observeErrors() = Unit

  fun handleUnknownError() =
    showLongSnackbar(R.string.api_error_unknown)
}

//endregion

//region Error - Bad Gateway

interface BadGatewayErrorTrait : ErrorTrait {

  fun handleBadGatewayError(occurred: Boolean) {

    // Display bad gateway error.
    if (occurred) {
      showLongSnackbar(R.string.api_error_server_error)
    }
  }
}

//endregion

//region Error - Business

interface BusinessErrorTrait : ErrorTrait {

  //region Mapping

  companion object {

    /**
     * A human-readable collection of strings for all business errors.
     */
    val businessErrors = mapOf(
      BusinessError.TODO to R.string.todo
    )
  }

  //endregion

  //region Strategy

  fun handleBusinessError(@BusinessError type: String) {

    // Log the fact.
    ILogger.w("A business error occurred: $type.")

    // Try to pick an error message by type.
    val errorRes = businessErrors[type]

    // Show an error message (or default for unsupported ones).
    errorRes
      ?.let { showLongSnackbar(it) }
      ?: run { handleUnknownError() }
  }

  //endregion
}

//endregion

//region Error - Server

interface ServerErrorTrait : ErrorTrait {

  fun handleNoServerError(occurred: Boolean) {

    // Display server not available error.
    if (occurred) {
      showLongSnackbar(R.string.network_server_unreachable)
    }
  }
}

//endregion

//region Error - Unauthorized

interface UnauthorizedErrorTrait : ErrorTrait {

  fun handleUnauthorizedError(occurred: Boolean) {

    // Display unauthorized error.
    if (occurred) {
      showLongSnackbar(R.string.api_error_user_unauthorized)
    }
  }
}

//endregion
