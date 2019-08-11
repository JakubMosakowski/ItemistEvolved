package com.jakmos.itemistevolved.presentation.checklists.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.presentation.checklists.view.ChecklistItemView

class ChecklistAdapter(private val listener: ChecklistAdapterListener? = null) :
    RecyclerView.Adapter<ChecklistAdapter.ChecklistAdapterViewHolder>() {

    private var items: List<Checklist> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChecklistAdapterViewHolder {
        val view = ChecklistItemView(parent.context)

        return ChecklistAdapterViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ChecklistAdapterViewHolder, position: Int) {
        holder.bind(listener, items[position])
    }

    fun setData(items: List<Checklist>) {
        this.items = items
    }

    interface ChecklistAdapterListener {
        fun onItemClick(model: Checklist)
        fun onEditClicked(model: Checklist)
    }

    class ChecklistAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(listener: ChecklistAdapterListener? = null, model: Checklist) {
            (itemView as? ChecklistItemView)?.bind(model, listener)
        }
    }
}