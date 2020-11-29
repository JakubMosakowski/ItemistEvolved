package com.jakmos.itemistevolved.application

import android.app.NotificationManager
import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

  //region Context

  @Provides
  internal fun provideApplicationContext(application: ItemistEvolved): Context =
    application

  @Provides
  internal fun provideApplicationResources(application: ItemistEvolved): Resources =
    application.resources

  //endregion

  //region Content Provider

  @Provides
  internal fun provideContentResolver(context: Context): ContentResolver =
    context.contentResolver

  //endregion

  //region Notification Manager

  @Provides
  @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
  internal fun provideNotificationManager(context: Context): NotificationManager =
    context.getSystemService(NotificationManager::class.java)

  //endregion
}
