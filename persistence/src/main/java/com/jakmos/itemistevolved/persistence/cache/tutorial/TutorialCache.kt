package com.jakmos.itemistevolved.persistence.cache.tutorial

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TutorialCache @Inject constructor(context: Context) :
  TutorialCacheProviderPrefs(context.requireTutorialCacheProvider())
