package com.jakmos.itemistevolved.domain.mapper

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mapstruct.factory.Mappers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MapperModule {

  //region Checklist

  @Provides
  @Singleton
  internal fun provideChecklistMapper(): ChecklistMapper =
    Mappers.getMapper(ChecklistMapper::class.java)

  //endregion
}
