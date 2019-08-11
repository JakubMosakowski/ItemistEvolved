package com.jakmos.itemistevolved.presentation.commons.adapter

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.jakmos.itemistevolved.presentation.commons.dp2px

class BottomItemDecoration(
    private val divider: Drawable,
    private val dividerHorizontalPadding: Int = dp2px(16).toInt()
) : RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = dividerHorizontalPadding
        val right = parent.width - dividerHorizontalPadding

        val childCount = parent.childCount
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            if (position == RecyclerView.NO_POSITION) return

            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + divider.intrinsicHeight

            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }
}
