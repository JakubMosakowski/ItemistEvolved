package com.jakmos.itemistevolved.presentation.checklists.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.jakmos.itemistevolved.domain.model.Checklist
import android.view.LayoutInflater
import com.jakmos.itemistevolved.databinding.ChecklistItemBinding


class ChecklistAdapter(private val listener: ChecklistAdapterListener? = null) :
    RecyclerView.Adapter<ChecklistAdapter.ChecklistAdapterViewHolder>() {

    private var items: List<Checklist> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChecklistAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChecklistItemBinding.inflate(inflater)

        return ChecklistAdapterViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ChecklistAdapterViewHolder, position: Int) =
        holder.bind(listener, items[position])


    fun setData(items: List<Checklist>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface ChecklistAdapterListener {
        fun onItemClicked(model: Checklist)
        fun onEditClicked(model: Checklist)
    }

    inner class ChecklistAdapterViewHolder(
        private val binding: ChecklistItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: ChecklistAdapterListener? = null, model: Checklist) {
            binding.model = model
            binding.listener = listener
        }
    }
}
