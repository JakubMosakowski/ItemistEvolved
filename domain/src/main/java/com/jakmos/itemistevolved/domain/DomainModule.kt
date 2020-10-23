package com.jakmos.itemistevolved.domain

import com.jakmos.itemistevolved.domain.mapper.MapperModule
import com.jakmos.itemistevolved.network.NetworkModule
import com.jakmos.itemistevolved.persistence.PersistenceModule
import com.jakmos.itemistevolved.utility.UtilityModule
import dagger.Module

@Module(includes = [
  MapperModule::class,
  NetworkModule::class,
  PersistenceModule::class,
  UtilityModule::class
])
class DomainModule
