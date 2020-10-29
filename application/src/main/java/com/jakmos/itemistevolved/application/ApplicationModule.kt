package com.jakmos.itemistevolved.application

import android.app.NotificationManager
import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import co.windly.limbo.dagger.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

  //region Context

  @Provides
  @ApplicationScope
  internal fun provideApplicationContext(application: ItemistEvolved): Context =
    application

  @Provides
  @ApplicationScope
  internal fun provideApplicationResources(application: ItemistEvolved): Resources =
    application.resources

  //endregion

  //region Content Provider

  @Provides
  @ApplicationScope
  internal fun provideContentResolver(context: Context): ContentResolver =
    context.contentResolver

  //endregion

  //region Notification Manager

  @Provides
  @ApplicationScope
  @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
  internal fun provideNotificationManager(context: Context): NotificationManager =
    context.getSystemService(NotificationManager::class.java)

  //endregion
}
