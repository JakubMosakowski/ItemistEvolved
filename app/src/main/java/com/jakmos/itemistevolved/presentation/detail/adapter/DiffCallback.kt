package com.jakmos.itemistevolved.presentation.detail.adapter

import androidx.recyclerview.widget.DiffUtil
import com.jakmos.itemistevolved.domain.model.Item


class DiffCallback(private val new: List<Item>,
                   private val old: List<Item>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }
}
