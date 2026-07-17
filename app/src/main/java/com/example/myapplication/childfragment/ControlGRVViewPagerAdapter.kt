package com.example.myapplication.childfragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ControlGRVViewPagerAdapter(fragment: Fragment, id: Int): FragmentStateAdapter(fragment) {

    private val mId: Int = id

    override fun createFragment(position: Int): Fragment {
        return ChildControlGRVViewPagerFragment.newInstance()
    }

    override fun getItemCount(): Int = 1

}