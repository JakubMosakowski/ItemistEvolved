package com.jakmos.itemistevolved.domain.model

import java.io.Serializable

data class Item(
    val text: String,
    val isChecked: Boolean
) : Serializable {
    companion object {
        fun create(text: String) = Item(text, false)
    }
}
