package com.jakmos.itemistevolved.presentation.main.checklist.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.databinding.ItemCheckboxBinding
import com.jakmos.itemistevolved.domain.model.Subsection
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem


class CheckboxItem(
  model: Subsection
) : ModelAbstractBindingItem<Subsection, ItemCheckboxBinding>(model) {

  //region Initialization

  init {

    // Define identifier.
    identifier = model.id
  }

  //endregion

  //region Type

  override val type: Int
    get() = R.id.itemCheckbox

  //endregion

  //region Binding

  override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?)
    : ItemCheckboxBinding = ItemCheckboxBinding.inflate(inflater, parent, false)

  override fun bindView(binding: ItemCheckboxBinding, payloads: List<Any>) {
    super.bindView(binding, payloads)

    // Update binding.
    updateCheckboxBinding(binding)
  }

  private fun updateCheckboxBinding(binding: ItemCheckboxBinding) = with(binding) {

    // Send subsection data to layout.
    subsection = model
  }

  //endregion
}
