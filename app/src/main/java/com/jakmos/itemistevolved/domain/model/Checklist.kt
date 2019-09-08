package com.jakmos.itemistevolved.domain.model

import java.io.Serializable
import java.util.*

data class Checklist(
    val id: Long,
    val name: String,
    val image: String,
    val lines: List<Item>,
    val createdAt: Date? = null
) : Serializable {
    fun atLeastOneClicked() = lines.any { it.isChecked }
    private fun countChecked() = lines.count { it.isChecked }
    fun getCounterText() = "${countChecked()} / ${lines.size}"

    companion object {
        fun create() = Checklist(0, "", "", emptyList())
    }
}
