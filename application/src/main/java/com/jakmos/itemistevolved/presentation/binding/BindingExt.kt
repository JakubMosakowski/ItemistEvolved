package com.jakmos.itemistevolved.presentation.binding

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.mikepenz.fastadapter.binding.BindingViewHolder

//region Binding

inline fun <reified T : ViewBinding> ViewHolder.asBinding(): T? =
  when (this is BindingViewHolder<*> && binding is T) {
    true -> binding as T
    false -> null
  }

//endregion
