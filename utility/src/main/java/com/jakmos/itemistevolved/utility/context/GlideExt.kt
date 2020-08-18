package com.jakmos.itemistevolved.utility.context

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition

//region Glide

class RequestListener<TranscodeType>(
  private var onComplete: () -> Unit = {}
) : RequestListener<TranscodeType> {

  //region Interface

  override fun onResourceReady(resource: TranscodeType?, model: Any?,
    target: Target<TranscodeType>?,
    dataSource: DataSource?, isFirstResource: Boolean): Boolean = false.also {

    // Pass control to the listener.
    onComplete.invoke()
  }

  override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<TranscodeType>?,
    isFirstResource: Boolean): Boolean = false.also {

    // Pass control to the listener.
    onComplete.invoke()
  }

  //endregion
}

fun <TranscodeType> GlideRequest<TranscodeType>.onComplete(onComplete: () -> Unit) =
  apply { this.listener(RequestListener(onComplete)) }

fun <T : ImageView> T.loadCircleImage(url: String, @DrawableRes placeholder: Int = View.NO_ID) {

  // Load circle image.
  GlideApp
    .with(this)
    .load(url)
    .placeholder(placeholder)
    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    .circleCrop()
    .into(this)
}

fun <T : ImageView> T.loadImage(url: String, @DrawableRes placeholder: Int = View.NO_ID) {

  // Load image.
  GlideApp
    .with(this)
    .load(url)
    .placeholder(placeholder)
    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    .into(this)
}

fun <T : ImageView> T.loadCenterCropImage(url: String, @DrawableRes placeholder: Int = View.NO_ID) {

  // Load image.
  GlideApp
    .with(this)
    .load(url)
    .placeholder(placeholder)
    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    .centerCrop()
    .into(this)
}

fun <T : ImageView> T.loadFitCenterImage(url: String, @DrawableRes placeholder: Int = View.NO_ID) {

  // Load image.
  GlideApp
    .with(this)
    .load(url)
    .placeholder(placeholder)
    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    .fitCenter()
    .into(this)
}

fun <T : ImageView> T.clearImage() {

  // Clear image.
  GlideApp.with(this).clear(this)
  setImageDrawable(null)
}

fun MenuItem.loadCircleIcon(context: Context, url: String, @DrawableRes placeholder: Int) {

  // Load icon.
  GlideApp
    .with(context)
    .load(url)
    .error(placeholder)
    .placeholder(placeholder)
    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    .circleCrop()
    .into(object : CustomTarget<Drawable>() {

      override fun onLoadFailed(errorDrawable: Drawable?) {
        super.onLoadFailed(errorDrawable)

        // Set icon to error drawable.
        icon = errorDrawable
      }

      override fun onLoadCleared(placeholder: Drawable?) {

        // Set icon to provided placeholder.
        icon = placeholder
      }

      override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {

        // If SDK version is above O, it's necessary to set tint list and tint mode to null.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          iconTintList = null
          iconTintMode = null
        }

        // Set icon to provided resource.
        icon = resource
      }
    })
}

@SuppressLint("CheckResult")
fun <T : ImageView> T.loadImage(
  url: String,
  onComplete: () -> Unit = {},
  @DimenRes imageRadius: Int? = null,
  @DrawableRes placeholder: Int = View.NO_ID,
  transformation: Transformation<Bitmap> = CenterInside()
) {

  // Prepare glide request.
  val glideRequest =
    GlideApp
      .with(context)
      .load(url)
      .placeholder(placeholder)
      .onComplete(onComplete::invoke)
      .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

  if (imageRadius != null) {

    // Prepare radius of image.
    val radius =
      context
        .resources
        .getDimensionPixelSize(imageRadius)

    // Handle rounded corners transformation.
    glideRequest.transform(transformation, RoundedCorners(radius))
  }

  // Load image.
  glideRequest.into(this)
}

//endregion
