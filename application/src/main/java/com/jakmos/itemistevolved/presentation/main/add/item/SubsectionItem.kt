package com.jakmos.itemistevolved.presentation.main.add.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.ItemSubsectionBinding
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem

class SubsectionItem(
  model: SimpleSubsection) : ModelAbstractBindingItem<SimpleSubsection, ItemSubsectionBinding>(
  model) {

  //region Initialization

  init {

    // Define identifier.
    identifier = model.id
  }

  //endregion

  //region Type

  override val type: Int
    get() = R.id.itemSubsection

  //endregion

  //region Binding

  override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?)
    : ItemSubsectionBinding = ItemSubsectionBinding.inflate(inflater, parent, false)

  override fun bindView(binding: ItemSubsectionBinding, payloads: List<Any>) {
    super.bindView(binding, payloads)

    // Update binding.
    updateSubsectionBinding(binding)
  }

  private fun updateSubsectionBinding(binding: ItemSubsectionBinding) = with(binding) {

    // Send subsection data to layout.
    subsection = model
  }

  //endregion
}
