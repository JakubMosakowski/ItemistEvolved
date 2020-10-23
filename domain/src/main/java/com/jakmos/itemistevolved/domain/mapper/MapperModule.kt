package com.jakmos.itemistevolved.domain.mapper

import dagger.Module
import dagger.Provides
import org.mapstruct.factory.Mappers
import javax.inject.Singleton

@Module
class MapperModule {

  //region Checklist

  @Provides
  @Singleton
  internal fun provideChecklistMapper(): ChecklistMapper =
    Mappers.getMapper(ChecklistMapper::class.java)

  //endregion
}
