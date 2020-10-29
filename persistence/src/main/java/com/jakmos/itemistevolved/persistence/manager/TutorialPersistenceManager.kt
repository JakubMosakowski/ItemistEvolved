package com.jakmos.itemistevolved.persistence.manager

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.annotations.SchedulerSupport
import com.jakmos.itemistevolved.persistence.cache.tutorial.TutorialCache
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@SchedulerSupport(value = SchedulerSupport.IO)
class TutorialPersistenceManager @Inject constructor(
  private val tutorialCache: TutorialCache
) {

  //region Tutorial Viewed

  //TODO stop using rxjava
  fun getTutorialSeen(): Single<Boolean> =
    tutorialCache.getRxTutorialSeen()

  fun setTutorialSeen(wasSeen: Boolean): Completable =
    tutorialCache.setRxTutorialSeen(wasSeen)

  //endregion
}
