package com.example.myapplication.childfragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.model.ControlGRV

class ControlGRVViewPagerAdapter(fragment: Fragment, private val controls: MutableList<ControlGRV>): FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        return if (position == controls.size) ChildAddingPageGRVViewPager.newInstance()
        else ChildControlGRVViewPagerFragment.newInstance(controls[position])
    }

    override fun getItemId(position: Int): Long = if (position == controls.size) Long.MAX_VALUE else controls[position].pageId.toLong()

    override fun containsItem(itemId: Long): Boolean = itemId == Long.MAX_VALUE || controls.any { it.pageId.toLong() == itemId }

    override fun getItemCount(): Int = controls.size + 1

}