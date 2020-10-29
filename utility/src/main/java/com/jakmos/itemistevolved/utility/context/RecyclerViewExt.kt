package com.jakmos.itemistevolved.utility.context

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.jakmos.itemistevolved.utility.context.RecyclerViewConstants.SPAN_SIZE_DEFAULT

//region Constants

private object RecyclerViewConstants {
  const val SPAN_SIZE_DEFAULT = 2
}

//endregion

//region Layout Manager

fun RecyclerView.addFlexBoxLayoutManager() {
  layoutManager = FlexboxLayoutManager(context)
}

fun RecyclerView.addGridLayoutManager(
  spanSizeTotal: Int = SPAN_SIZE_DEFAULT,
  spanSizeLookup: GridLayoutManager.SpanSizeLookup? = null
) {

  // Set grid layout manager.
  layoutManager = GridLayoutManager(context, spanSizeTotal)

  // Apply span size lookup if needed.
  spanSizeLookup?.let { (layoutManager as GridLayoutManager).spanSizeLookup = spanSizeLookup }
}

fun RecyclerView.addReversedLinearLayoutManager() {
  layoutManager =
    LinearLayoutManager(context)
      .also { it.reverseLayout = true }
      .also { it.stackFromEnd = false }
}

//endregion

//region Scroll

fun RecyclerView.canScrollUp(): Boolean =
  canScrollVertically(-1)

fun RecyclerView.canScrollDown(): Boolean =
  canScrollVertically(1)

//endregion
