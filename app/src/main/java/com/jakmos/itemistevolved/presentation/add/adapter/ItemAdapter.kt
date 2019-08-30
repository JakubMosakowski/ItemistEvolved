package com.jakmos.itemistevolved.presentation.add.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jakmos.itemistevolved.databinding.LineItemBinding
import com.jakmos.itemistevolved.domain.model.Item
import timber.log.Timber

class ItemAdapter(private val listener: ItemAdapterListener? = null) :
    RecyclerView.Adapter<ItemAdapter.ItemAdapterViewHolder>() {

    private var items: List<Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LineItemBinding.inflate(inflater, parent, false)

        return ItemAdapterViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemAdapterViewHolder, position: Int) {
        Timber.tag("KUBA").v("onBindViewHolder ${items[position]}")
        holder.bind(listener, items[position])
    }

    fun setData(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface ItemAdapterListener {
        fun onDeleteClicked(model: Item)
    }

    class ItemAdapterViewHolder(
        private val binding: LineItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: ItemAdapterListener? = null, model: Item) {
            binding.model = model
            Timber.tag("KUBA").v("bind ${binding.model}")
            binding.listener = listener
        }
    }
}
