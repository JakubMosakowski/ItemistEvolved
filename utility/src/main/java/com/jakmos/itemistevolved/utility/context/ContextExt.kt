package com.jakmos.itemistevolved.utility.context

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import co.windly.limbo.utility.primitives.EMPTY
import java.io.File
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

//region Uri

fun Context.getUriForFile(file: File): Uri =
  FileProvider.getUriForFile(this, "${this.applicationContext.packageName}.provider", file)

//endregion

//region Share

fun Context.shareViaIntent(
  textToShare: String,
  intentType: String,
  chooserTitle: String = String.EMPTY
) {

  // Prepare share intent.
  val shareIntent = Intent()
    .apply { action = Intent.ACTION_SEND }
    .apply { putExtra(Intent.EXTRA_TEXT, textToShare) }
    .apply { type = intentType }

  // Prepare intent chooser.
  val chooserIntent =
    Intent
      .createChooser(shareIntent, chooserTitle)

  // Navigate to chooser.
  startActivity(chooserIntent)
}

//endregion

//region Email

fun Context.sendEmail(
  subject: String = String.EMPTY,
  addresses: Array<String> = arrayOf(),
  chooserTitle: String = String.EMPTY
) {

  // Prepare email intent.
  val emailIntent = Intent(Intent.ACTION_SENDTO)
    .apply { data = "mailto:".toUri() }
    .apply { putExtra(Intent.EXTRA_EMAIL, addresses) }
    .apply { putExtra(Intent.EXTRA_SUBJECT, subject) }
    .apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }

  // Prepare intent chooser.
  val chooserIntent =
    Intent
      .createChooser(emailIntent, chooserTitle)

  // Navigate to email app chooser.
  startActivity(chooserIntent)
}

//endregion

//region Web Browser

fun Context.openWebBrowser(
  urlString: String
) {

  // Prepare browser intent.
  val browserIntent = Intent(Intent.ACTION_VIEW)
    .apply { data = Uri.parse(urlString) }

  // Start system web browser.
  startActivity(browserIntent)
}

//endregion

//region Hashkey

fun Context.printHashKey() {
  try {
    val info: PackageInfo = packageManager.getPackageInfo(packageName,
      PackageManager.GET_SIGNATURES)
    for (signature in info.signatures) {
      val md: MessageDigest = MessageDigest.getInstance("SHA")
      md.update(signature.toByteArray())
      val hashKey = String(Base64.encode(md.digest(), 0))
      Log.i("HASH", "printHashKey() Hash Key: $hashKey")
    }
  } catch (e: NoSuchAlgorithmException) {
    Log.e("HASH", "printHashKey()")
  } catch (e: Exception) {
    Log.e("HASH", "printHashKey()")
  }
}

//endregion
