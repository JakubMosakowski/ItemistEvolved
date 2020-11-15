package com.jakmos.itemistevolved.network

import com.jakmos.itemistevolved.network.remoteconfig.RemoteConfigModule
import dagger.Module

@Module(
  includes = [
    RemoteConfigModule::class
  ]
)
class NetworkModule
