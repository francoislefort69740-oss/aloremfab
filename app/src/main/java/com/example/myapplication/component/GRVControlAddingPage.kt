package com.example.myapplication.component

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Guideline
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GRVControlAddingPage {

    private var pageId: Int = 0

    private lateinit var mView: View

    fun setUp(view: View, arguments: Bundle) {
        mView = view
        view.findViewById<RecyclerView>(R.id.recycler_child_control_grv)
        view.findViewById<TextView>(R.id.step_title_child_control_grv).visibility = View.GONE
        view.findViewById<FloatingActionButton>(R.id.check_all_ok_child_control_grv).visibility = View.GONE
        view.findViewById<FloatingActionButton>(R.id.save_child_control_grv).visibility = View.GONE
        view.findViewById<FloatingActionButton>(R.id.back_child_control_grv).visibility = View.GONE
        view.findViewById<FloatingActionButton>(R.id.next_child_control_grv).visibility = View.GONE
        view.findViewById<ImageView>(R.id.close_child_control_grv).visibility = View.GONE

        view.findViewById<ImageView>(R.id.add_child_control_grv).visibility = View.VISIBLE
        view.findViewById<Guideline>(R.id.menu_guideline_3_child_control_grv).setGuidelinePercent(0.68F)
        view.findViewById<ImageView>(R.id.update_grv_list_control_grv).visibility = View.VISIBLE

    }

    fun addingButton(): ImageView = mView.findViewById(R.id.add_child_control_grv)
    fun updateButton(): ImageView = mView.findViewById(R.id.update_grv_list_control_grv)
}