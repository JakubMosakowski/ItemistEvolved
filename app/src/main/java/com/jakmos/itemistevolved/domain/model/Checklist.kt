package com.jakmos.itemistevolved.domain.model

import java.io.Serializable
import java.util.*

data class Checklist(
    val id: Long,
    val name: String,
    val image: String,
    val lines: List<Item>,
    val createdAt: Date? = null
) : Serializable