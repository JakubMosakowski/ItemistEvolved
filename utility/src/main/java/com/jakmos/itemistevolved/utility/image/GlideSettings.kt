package com.jakmos.itemistevolved.utility.image

import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class GlideSettings : AppGlideModule() {

  //region Options

  override fun applyOptions(context: Context, builder: GlideBuilder) {
    super.applyOptions(context, builder)

    // Configure log level.
    builder.setLogLevel(Log.ERROR)

    // Configure request options.
    builder.setDefaultRequestOptions(
      RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
    )
  }

  //endregion
}
