package com.jakmos.itemistevolved.presentation.checklists.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.presentation.checklists.adapter.ChecklistAdapter
import com.jakmos.itemistevolved.presentation.commons.dp2px
import com.jakmos.itemistevolved.presentation.commons.inflate
import com.jakmos.itemistevolved.presentation.commons.loadForegroundDrawable
import kotlinx.android.synthetic.main.checklist_item.view.*


class ChecklistItemView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RelativeLayout(context, attrs, defStyle), View.OnClickListener {

    private var model: Checklist? = null
    private var listener: ChecklistAdapter.ChecklistAdapterListener? = null

    init {
        inflate(R.layout.checklist_item, true)

        setPadding(DOUBLE_PADDING, PADDING, DOUBLE_PADDING, PADDING)
        isFocusable = true
        isClickable = true
        foreground = context.loadForegroundDrawable()

        setOnClickListener(this)
        editBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        model?.let {
            when (v?.id) {
                R.id.editBtn -> listener?.onEditClicked(it)
                else -> listener?.onItemClick(it)
            }
        }
    }

    fun bind(model: Checklist, listener: ChecklistAdapter.ChecklistAdapterListener?) {
        this.model = model
        this.listener = listener

        buildView()
    }

    private fun buildView() {
        model?.let {
            title.text = it.name
        }
    }

    companion object {
        private val PADDING = dp2px(8).toInt()
        private val DOUBLE_PADDING = dp2px(8).toInt() * 2
    }
}