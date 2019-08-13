package com.jakmos.itemistevolved.domain.model.project

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.reflect.ParameterizedType

abstract class Parsable<T> : KoinComponent {

    protected val moshi: Moshi by inject()

    protected abstract val type: ParameterizedType
    protected abstract val adapter: JsonAdapter<T>

    @TypeConverter
    abstract fun stringToJson(data: String): T
    @TypeConverter
    abstract fun jsonToString(json: T): String
}