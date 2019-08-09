package com.jakmos.itemistevolved.data.converter

import androidx.room.TypeConverter
import com.jakmos.itemistevolved.domain.model.Item
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types.newParameterizedType
import java.lang.reflect.ParameterizedType

class ItemListConverter : ListConverter<Item>() {

    override val type: ParameterizedType = newParameterizedType(List::class.java, Item::class.java)
    override val adapter: JsonAdapter<List<Item>> = moshi.adapter(type)

    @TypeConverter
    override fun stringToJson(data: String): List<Item> {
        return adapter.fromJson(data) ?: emptyList()
    }

    @TypeConverter
    override fun jsonToString(list: List<Item>): String {
        return adapter.toJson(list)
    }
}