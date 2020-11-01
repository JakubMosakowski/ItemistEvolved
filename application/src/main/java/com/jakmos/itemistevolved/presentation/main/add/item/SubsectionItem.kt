package com.jakmos.itemistevolved.presentation.main.add.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.ItemSubsectionBinding
import com.mikepenz.fastadapter.binding.BindingViewHolder
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import com.mikepenz.fastadapter.drag.IExtendedDraggable
import com.mikepenz.fastadapter.utils.DragDropUtil

class SubsectionItem(
  model: SimpleSubsection,
  touchHelper: ItemTouchHelper
) : ModelAbstractBindingItem<SimpleSubsection, ItemSubsectionBinding>(
  model), IExtendedDraggable<RecyclerView.ViewHolder> {

  //region Initialization

  init {

    // Define identifier.
    identifier = model.id
  }

  //endregion

  //region Type

  override val type: Int
    get() = R.id.itemSubsection

  //endregion

  //region Binding

  override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?)
    : ItemSubsectionBinding = ItemSubsectionBinding.inflate(inflater, parent, false)

  override fun bindView(binding: ItemSubsectionBinding, payloads: List<Any>) {
    super.bindView(binding, payloads)

    // Update binding.
    updateSubsectionBinding(binding)
  }

  override fun bindView(holder: BindingViewHolder<ItemSubsectionBinding>, payloads: List<Any>) {
    super.bindView(holder, payloads)

    DragDropUtil.bindDragHandle(holder, this)
  }

  private fun updateSubsectionBinding(binding: ItemSubsectionBinding) = with(binding) {

    // Send subsection data to layout.
    subsection = model
  }

  //endregion

  //region Drag

  override fun getDragView(viewHolder: RecyclerView.ViewHolder): View? {
    if (viewHolder !is BindingViewHolder<*>) return null

    return (viewHolder.binding as? ItemSubsectionBinding)?.handle
  }

  override val touchHelper: ItemTouchHelper? =
    touchHelper

  override val isDraggable: Boolean =
    true

  //endregion
}
