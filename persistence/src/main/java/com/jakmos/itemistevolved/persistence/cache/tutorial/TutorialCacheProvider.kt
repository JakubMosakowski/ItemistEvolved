package com.jakmos.itemistevolved.persistence.cache.tutorial

import co.windly.ktxprefs.annotation.DefaultBoolean
import co.windly.ktxprefs.annotation.Name
import co.windly.ktxprefs.annotation.Prefs

@Prefs(fileName = "com.jakmos.itemistevolved.TUTORIAL")
data class TutorialCacheProvider(

  //region Tutorial Seen

  @Name(value = "seen")
  @DefaultBoolean(value = false)
  internal var tutorialSeen: Boolean

  //endregion
)
