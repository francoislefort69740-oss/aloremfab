package com.example.myapplication.childfragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.model.ControlGRV

class ControlGRVViewPagerAdapter(fragment: Fragment, controlGRV: ControlGRV): FragmentStateAdapter(fragment) {

    private val controls = mutableListOf(controlGRV)

    override fun createFragment(position: Int): Fragment {
        return ChildControlGRVViewPagerFragment.newInstance(controlGRV = controls[position])
    }

    fun addControl(controlGRV: ControlGRV): Int {
        val index = maxOf(0, controls.size - 1)

        controls.add(index, controlGRV)
        notifyItemInserted(index)

        return index
    }

    /**
     * Supprime la page à la position indiquée.
     */
    fun removeControl(position: Int) {

        if (position !in controls.indices)
            return

        controls.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getControl(position: Int): ControlGRV =
        controls[position]

    override fun getItemId(position: Int): Long =
        controls[position].pageId.toLong()

    override fun containsItem(itemId: Long): Boolean =
        controls.any { it.pageId.toLong() == itemId }

    override fun getItemCount(): Int = controls.size

}