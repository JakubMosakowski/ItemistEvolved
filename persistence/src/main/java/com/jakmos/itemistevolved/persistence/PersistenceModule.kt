package com.jakmos.itemistevolved.persistence

import dagger.Module
import com.jakmos.itemistevolved.persistence.database.DatabaseModule

@Module(includes = [
  DatabaseModule::class
])
class PersistenceModule
