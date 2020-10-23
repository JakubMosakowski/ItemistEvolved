package com.jakmos.itemistevolved.persistence.database

import android.content.Context
import androidx.room.Room
import com.jakmos.itemistevolved.persistence.database.dao.ChecklistDao
import com.jakmos.itemistevolved.persistence.database.dao.SubsectionDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

  //region Dao

  @Provides
  @Singleton
  internal fun provideChecklistDao(database: AndroidDatabase): ChecklistDao =
    database.checklistDao()

  @Provides
  @Singleton
  internal fun provideSubsectionDao(database: AndroidDatabase): SubsectionDao =
    database.subsectionDao()

  //endregion

  //region Database

  @Provides
  @Singleton
  internal fun provideDatabase(context: Context): AndroidDatabase =
    Room
      .databaseBuilder(context, AndroidDatabase::class.java, Configuration.NAME)
      .fallbackToDestructiveMigration()
      .build()

  //endregion

  //region Configuration

  internal object Configuration {

    internal const val NAME = "ItemistEvolved.db"
  }

  //endregion
}
