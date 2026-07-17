package com.example.myapplication.childfragment

import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.fragment.BaseFragment

class ChildControlGRVViewPagerFragment: BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_child_control_grv_view_pager

    override fun getBody(view: View, savedInstanceState: Bundle?) {

    }

    companion object {
        fun newInstance() = ChildControlGRVViewPagerFragment()
    }
}