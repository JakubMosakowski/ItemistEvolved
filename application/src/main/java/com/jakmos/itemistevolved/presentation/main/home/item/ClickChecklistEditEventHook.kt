package com.jakmos.itemistevolved.presentation.main.home.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jakmos.itemistevolved.databinding.ItemChecklistBinding
import com.jakmos.itemistevolved.presentation.binding.asBinding
import com.jakmos.itemistevolved.utility.vocabulary.Id
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook

class ClickChecklistEditEventHook(
  private val listener: (Id) -> Unit) : ClickEventHook<ChecklistItem>() {

  //region Bind

  override fun onBind(viewHolder: ViewHolder): View? =
    viewHolder
      .asBinding<ItemChecklistBinding>()
      ?.editBtn

  //endregion

  //region Click

  override fun onClick(v: View, position: Int, fastAdapter: FastAdapter<ChecklistItem>,
    item: ChecklistItem) {

    // Invoke listener.
    listener.invoke(item.model.id)
  }

  //endregion
}
