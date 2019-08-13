package com.jakmos.itemistevolved.domain.model

import java.io.Serializable

data class Checklist(
    val id: Long,
    val name: String,
    val image: String,
    val lines: List<Item>
) : Serializable