package com.jakmos.itemistevolved.presentation.main.add.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jakmos.itemistevolved.databinding.ItemSubsectionBinding
import com.jakmos.itemistevolved.domain.model.Subsection
import com.jakmos.itemistevolved.presentation.binding.asBinding
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook

class ClickSubsectionDeleteEventHook(
  private val listener: (Subsection) -> Unit) : ClickEventHook<SubsectionItem>() {

  //region Bind

  override fun onBind(viewHolder: ViewHolder): View? =
    viewHolder
      .asBinding<ItemSubsectionBinding>()
      ?.deleteBtn

  //endregion

  //region Click

  override fun onClick(v: View, position: Int, fastAdapter: FastAdapter<SubsectionItem>,
    item: SubsectionItem) {

    // Invoke listener.
    listener.invoke(item.model)
  }

  //endregion
}
