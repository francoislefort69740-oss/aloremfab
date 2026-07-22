package com.example.myapplication.component

import android.view.View
import android.widget.ImageView
import com.example.myapplication.R

class GRVControlAddingPage(view: View) {

    private var pageId: Int = 0

    private var mView: View = view

    fun addingButton(): ImageView = mView.findViewById(R.id.add_child_control_grv)
    fun updateButton(): ImageView = mView.findViewById(R.id.update_grv_list_control_grv)
}