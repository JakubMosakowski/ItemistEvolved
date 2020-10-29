package com.jakmos.itemistevolved.presentation.main.add


//class ItemAdapter(private val listener: ItemAdapterListener? = null) :
//    RecyclerView.Adapter<ItemAdapter.ItemAdapterViewHolder>() {
//
//    private var items: List<Item> = emptyList()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapterViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = LineItemBinding.inflate(inflater, parent, false)
//
//        val vh = ItemAdapterViewHolder(binding)
//        vh.itemView.handle.setOnTouchListener { _, event ->
//            if (event?.actionMasked == MotionEvent.ACTION_DOWN)
//                listener?.startDragging(vh)
//
//            return@setOnTouchListener true
//        }
//
//        return vh
//    }
//
//    override fun getItemCount() = items.size
//
//    override fun onBindViewHolder(holder: ItemAdapterViewHolder, position: Int) {
//        holder.bind(listener, items[position])
//    }
//
//    fun setData(items: List<Item>) {
//        this.items = items
//        notifyDataSetChanged()
//    }
//
//    interface ItemAdapterListener {
//        fun onDeleteClicked(model: Item)
//        fun startDragging(viewHolder: RecyclerView.ViewHolder)
//    }
//
//    class ItemAdapterViewHolder(
//        private val binding: LineItemBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(listener: ItemAdapterListener? = null, model: Item) {
//            binding.model = model
//            binding.listener = listener
//        }
//    }
//}
