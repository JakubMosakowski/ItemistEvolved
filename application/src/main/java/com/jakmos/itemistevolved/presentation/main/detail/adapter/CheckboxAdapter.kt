package com.jakmos.itemistevolved.presentation.main.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.jakmos.itemistevolved.domain.model.Subsection

//class CheckboxAdapter(private val listener: CheckboxAdapterListener? = null) :
//    RecyclerView.Adapter<CheckboxAdapterViewHolder>() {
//
//    private var items: List<Subsection> = emptyList()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckboxAdapterViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = CheckboxItemBinding.inflate(inflater, parent, false)
//
//        return CheckboxAdapterViewHolder(binding)
//    }
//
//    override fun getItemCount() = items.size
//
//    override fun onBindViewHolder(holder: CheckboxAdapterViewHolder, position: Int) {
//        holder.bind(listener, items[position])
//    }
//
//    fun setData(items: List<Subsection>) {
//        val diffResult = DiffUtil.calculateDiff(DiffCallback(this.items, items))
//        this.items = items
//        diffResult.dispatchUpdatesTo(this)
//    }
//
//    interface CheckboxAdapterListener {
//        fun onItemClicked(model: Subsection)
//    }
//
//    class CheckboxAdapterViewHolder(
//        private val binding: CheckboxItemBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(listener: CheckboxAdapterListener? = null, model: Subsection) {
//            binding.model = model
//            binding.listener = listener
//        }
//    }
//}
