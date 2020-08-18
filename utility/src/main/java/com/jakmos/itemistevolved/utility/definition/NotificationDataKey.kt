package com.jakmos.itemistevolved.utility.definition

import androidx.annotation.StringDef

import kotlin.annotation.AnnotationRetention.SOURCE

@StringDef(

)
@Retention(SOURCE)
annotation class NotificationDataKey {

  companion object {

    const val NOTIFICATION_TYPE = "notification_type"

  }
}
