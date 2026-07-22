package com.example.myapplication.component

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Guideline
import com.example.myapplication.R
import com.example.myapplication.childfragment.ChildControlGRVViewPagerFragment.Companion.GRV_ID
import com.example.myapplication.childfragment.ChildControlGRVViewPagerFragment.Companion.GRV_PAGE_ID
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

class GRVControlProcess {

    private var pageId: Int = 0
    private lateinit var mView: View

    fun setUp(view: View, arguments: Bundle) {
        mView = view
        view.findViewById<TextView>(R.id.step_title_child_control_grv).visibility = View.VISIBLE
        view.findViewById<FloatingActionButton>(R.id.check_all_ok_child_control_grv).visibility = View.VISIBLE
        view.findViewById<FloatingActionButton>(R.id.save_child_control_grv).visibility = View.VISIBLE
        view.findViewById<FloatingActionButton>(R.id.back_child_control_grv).visibility = View.VISIBLE
        view.findViewById<FloatingActionButton>(R.id.next_child_control_grv).visibility = View.VISIBLE
        view.findViewById<ImageView>(R.id.close_child_control_grv).visibility = View.VISIBLE
        view.findViewById<ImageView>(R.id.add_child_control_grv).visibility = View.GONE
        view.findViewById<ImageView>(R.id.update_grv_list_control_grv).visibility = View.GONE
        view.findViewById<Guideline>(R.id.menu_guideline_3_child_control_grv).setGuidelinePercent(0.9F)

        pageId = arguments.getInt(GRV_PAGE_ID)

        if (arguments.getInt(GRV_ID) == 0) {
            setUpFirstTime()
        }
    }

    fun setUpNextButton(state: Boolean) {
        mView.findViewById<FloatingActionButton>(R.id.next_child_control_grv).isEnabled = state
        mView.findViewById<FloatingActionButton>(R.id.next_child_control_grv).visibility = if (state) View.VISIBLE else View.INVISIBLE
    }

    fun setUpBackButton(state: Boolean) {
        mView.findViewById<FloatingActionButton>(R.id.back_child_control_grv).isEnabled = state
        mView.findViewById<FloatingActionButton>(R.id.back_child_control_grv).visibility = if (state) View.VISIBLE else View.INVISIBLE
    }

    fun setUpCheckButtons(state: Boolean) {
        mView.findViewById<FloatingActionButton>(R.id.check_all_ok_child_control_grv).isEnabled = state
        mView.findViewById<FloatingActionButton>(R.id.check_all_ok_child_control_grv).visibility = if (state) View.VISIBLE else View.INVISIBLE
    }

    fun setUpFirstTime(){
        setUpBackButton(state = false)
        setUpNextButton(state = false)
        setUpCheckButtons(state = false)
    }

    fun closeButton(): ImageView = mView.findViewById(R.id.close_child_control_grv)

    fun getPageId(): Int = pageId

    fun save(): FloatingActionButton = mView.findViewById(R.id.save_child_control_grv)

    fun getFakeSerialNumber(): Int = Random.nextInt(1000000000)

}