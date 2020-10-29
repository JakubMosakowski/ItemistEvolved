package com.jakmos.itemistevolved.presentation.base.fragment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FragmentAdapter(manager: FragmentManager) :
  FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

  //region Types

  private var pages: MutableList<Pair<String, Fragment>> =
    mutableListOf()

  fun setPages(pages: List<Pair<String, Fragment>>) {

    // Cache pages.
    this.pages.clear()
    this.pages.addAll(pages)
  }

  //region Items

  override fun getCount(): Int =
    pages.size

  override fun getItem(position: Int): Fragment {

    // Return fragment.
    return pages[position].second
  }

  //endregion

  //region Page Title

  override fun getPageTitle(position: Int): CharSequence? =
    pages[position].first

  //endregion
}
