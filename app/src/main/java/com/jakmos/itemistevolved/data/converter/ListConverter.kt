package com.jakmos.itemistevolved.data.converter

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.reflect.ParameterizedType

abstract class ListConverter<T> : KoinComponent {
    protected val moshi: Moshi by inject()

    protected abstract val type: ParameterizedType
    protected abstract val adapter: JsonAdapter<List<T>>

    @TypeConverter
    abstract fun stringToJson(data: String): List<T>
    @TypeConverter
    abstract fun jsonToString(list: List<T>): String
}