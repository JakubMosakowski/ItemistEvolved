package com.jakmos.itemistevolved.presentation.common.recyclerview

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.drag.ItemTouchCallback
import com.mikepenz.fastadapter.drag.SimpleDragCallback


/**
 * Simple drag callback that makes items semi-transparent while they are being dragged.
 */
class TranslucentDragCallback(itemTouchCallback: ItemTouchCallback) : SimpleDragCallback(itemTouchCallback) {

  override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
    super.onSelectedChanged(viewHolder, actionState)

    if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
      viewHolder?.itemView?.alpha = 0.5f
    }
  }

  override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
    super.clearView(recyclerView, viewHolder)

    viewHolder.itemView.alpha = 1.0f
  }

}
