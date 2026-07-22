package com.example.myapplication.component

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Guideline
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.childfragment.ChildControlGRVViewPagerFragment.Companion.GRV_CONTROL
import com.example.myapplication.model.ControlGRV
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

class GRVControlProcess {

    private var pageId: Int = 0
    private lateinit var control: ControlGRV
    private lateinit var mView: View

    fun setUp(view: View, arguments: Bundle) {
        mView = view
        setUpFirstTime()
        arguments.getParcelable(GRV_CONTROL, ControlGRV::class.java)?.let {
            control = it
            if (control.serialNumber == 0) { setUpFirstTime() }
            pageId = control.pageId
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

    fun closeButton(): ImageView {
        control.loaded = false
        return mView.findViewById(R.id.close_child_control_grv)
    }

    fun getControl(): ControlGRV = control

    fun save(): FloatingActionButton = mView.findViewById(R.id.save_child_control_grv)

    fun getFakeSerialNumber(): Int = Random.nextInt(1000000000)

}