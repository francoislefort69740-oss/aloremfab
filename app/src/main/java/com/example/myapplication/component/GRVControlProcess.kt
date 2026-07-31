package com.example.myapplication.component

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.myapplication.R
import com.example.myapplication.childfragment.ChildControlGRVViewPagerFragment.Companion.GRV_CONTROL
import com.example.myapplication.model.ControlGRV
import com.example.myapplication.model.ControlGRVCheckPoint
import com.example.myapplication.model.StepControlGRV
import com.example.myapplication.utils.grvControlProcess
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GRVControlProcess {

    private var pageId: Int = 0
    private lateinit var control: ControlGRV
    private lateinit var stepControlGRV: StepControlGRV
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

    fun incrementStep(): Int {
        control.currentStep += 1
        return control.currentStep
    }

    fun decrementStep(): Int {
        control.currentStep -= 1
        return control.currentStep
    }

    fun getControl(): ControlGRV = control

    fun save(): FloatingActionButton = mView.findViewById(R.id.save_child_control_grv)
    fun back(): FloatingActionButton = mView.findViewById(R.id.back_child_control_grv)
    fun next(): FloatingActionButton = mView.findViewById(R.id.next_child_control_grv)

    fun serialNumberExist(): Boolean = control.serialNumber != 0

    fun initializeNewControl(): StepControlGRV.Step0ControlGRV = StepControlGRV.Step0ControlGRV()

    // TRANSLATE CONTROL STEP TO CONTROL GRV

    fun translateControlStepToControlGRV(list : List<ControlGRVCheckPoint>, context: Context): Pair<ControlGRV, StepControlGRV> {
        if (control.currentStep == 0) {
                var serialNUmber = (list.find {
                    (it as ControlGRVCheckPoint.EditableCheckPoint).name == context.getString(R.string.control_grv_checkpoint_report_number)
                } as ControlGRVCheckPoint.EditableCheckPoint).value.toIntOrNull()

                if (serialNUmber == null || serialNUmber == 0) {
                    serialNUmber = null
                }

                control.serialNumber = serialNUmber
                control.uid = serialNUmber
        }

        stepControlGRV = grvControlProcess(control.currentStep, list, context, control.serialNumber)
        return Pair(control, stepControlGRV)
    }

}