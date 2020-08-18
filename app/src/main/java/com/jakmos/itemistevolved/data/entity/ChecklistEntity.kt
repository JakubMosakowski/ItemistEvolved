package com.jakmos.itemistevolved.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jakmos.itemistevolved.domain.model.Item
import java.util.*

@Entity(tableName = "checklist")
data class ChecklistEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,

    val image: String,

    val createdAt: Date = Date(),

    val updatedAt: Date = Date(),

    val lines: List<Item>
)
