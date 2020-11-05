package com.jakmos.itemistevolved.presentation.main.checklist.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jakmos.itemistevolved.databinding.ItemCheckboxBinding
import com.jakmos.itemistevolved.domain.model.Subsection
import com.jakmos.itemistevolved.presentation.binding.asBinding
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook

class ClickCheckboxEventHook(
  private val listener: (Subsection) -> Unit) : ClickEventHook<CheckboxItem>() {

  //region Bind

  override fun onBind(viewHolder: ViewHolder): View? =
    viewHolder
      .asBinding<ItemCheckboxBinding>()
      ?.checkbox

  //endregion

  //region Click

  override fun onClick(v: View, position: Int, fastAdapter: FastAdapter<CheckboxItem>,
    item: CheckboxItem) {

    // Invoke listener.
    listener.invoke(item.model)
  }

  //endregion
}
