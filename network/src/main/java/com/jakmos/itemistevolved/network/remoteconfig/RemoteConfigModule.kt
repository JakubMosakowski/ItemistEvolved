package com.jakmos.itemistevolved.network.remoteconfig

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteConfigModule {

  //region Remote Config - Settings

  @Provides
  @Singleton
  internal fun provideRemoteConfigSettings(): FirebaseRemoteConfigSettings =
    remoteConfigSettings {
    }

  //endregion

  //region Remote Config

  @Provides
  @Singleton
  internal fun provideRemoteConfig(
    settings: FirebaseRemoteConfigSettings
  ): FirebaseRemoteConfig =
    Firebase.remoteConfig
      .apply {
        setConfigSettingsAsync(settings)
      }

  //endregion
}
