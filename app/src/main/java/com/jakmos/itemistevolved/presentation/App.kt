package com.jakmos.itemistevolved.presentation

import android.app.Application
import com.jakmos.itemistevolved.presentation.commons.injection.module.appModule
import com.jakmos.itemistevolved.presentation.commons.injection.module.repositoryModule
import com.jakmos.itemistevolved.presentation.commons.injection.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import kotlin.math.log

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        startKoin {
                        androidLogger()
            androidContext(this@App)
            modules(listOf(appModule, repositoryModule, viewModelModule))
        }
    }

}