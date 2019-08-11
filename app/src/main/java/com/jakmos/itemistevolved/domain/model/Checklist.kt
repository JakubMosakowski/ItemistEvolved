package com.jakmos.itemistevolved.domain.model

data class Checklist(
    val id: Long,
    val name: String,
    val image: String,
    val lines: List<Item>
)
