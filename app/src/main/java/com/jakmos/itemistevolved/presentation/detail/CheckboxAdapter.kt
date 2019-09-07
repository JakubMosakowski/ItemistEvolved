package com.jakmos.itemistevolved.presentation.detail

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jakmos.itemistevolved.databinding.CheckboxItemBinding
import com.jakmos.itemistevolved.domain.model.Item

class CheckboxAdapter(private val listener: CheckboxAdapterListener? = null) :
    RecyclerView.Adapter<CheckboxAdapter.CheckboxAdapterViewHolder>() {

    private var items: List<Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckboxAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CheckboxItemBinding.inflate(inflater, parent, false)

        return CheckboxAdapterViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CheckboxAdapterViewHolder, position: Int) {
        holder.bind(listener, items[position])
    }

    fun setData(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface CheckboxAdapterListener {
        fun onItemClicked(model: Item)
    }

    class CheckboxAdapterViewHolder(
        private val binding: CheckboxItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: CheckboxAdapterListener? = null, model: Item) {
            binding.model = model
            binding.listener = listener
        }
    }
}
