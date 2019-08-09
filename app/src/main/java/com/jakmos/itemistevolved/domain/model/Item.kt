package com.jakmos.itemistevolved.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val text: String,
    val isChecked: Boolean
)