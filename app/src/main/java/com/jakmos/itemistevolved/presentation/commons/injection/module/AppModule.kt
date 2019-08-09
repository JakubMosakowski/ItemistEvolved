package com.jakmos.itemistevolved.presentation.commons.injection.module

import com.squareup.moshi.Moshi
import org.koin.dsl.module

val appModule = module {

    single<Moshi> {
        Moshi.Builder()
            .build()
    }
}