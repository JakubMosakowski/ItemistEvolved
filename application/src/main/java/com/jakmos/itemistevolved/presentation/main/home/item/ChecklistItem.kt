package com.jakmos.itemistevolved.presentation.main.home.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.ItemChecklistBinding
import com.jakmos.itemistevolved.domain.model.Checklist
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem


class ChecklistItem(model: Checklist) : ModelAbstractBindingItem<Checklist, ItemChecklistBinding>(
  model) {

  //region Initialization

  init {

    // Define identifier.
    identifier = model.id
  }

  //endregion

  //region Type

  override val type: Int
    get() = R.id.itemChecklist

  //endregion

  //region Binding

  override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?)
    : ItemChecklistBinding = ItemChecklistBinding.inflate(inflater, parent, false)

  override fun bindView(binding: ItemChecklistBinding, payloads: List<Any>) {
    super.bindView(binding, payloads)

    // Update binding.
    updateChecklistBinding(binding)
  }

  private fun updateChecklistBinding(binding: ItemChecklistBinding) = with(binding) {

    // Send checklist data to layout.
    checklist = model
  }

  //endregion
}
