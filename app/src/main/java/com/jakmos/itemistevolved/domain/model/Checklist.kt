package com.jakmos.itemistevolved.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jakmos.itemistevolved.data.converter.DateConverter
import com.jakmos.itemistevolved.data.converter.ItemListConverter
import java.util.*

@Entity(tableName = "checklist")
data class Checklist(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val name: String,
    val image: String,

    @TypeConverters(DateConverter::class)
    val createdAt: Date,

    @TypeConverters(ItemListConverter::class)
    val lines: List<Item>
)