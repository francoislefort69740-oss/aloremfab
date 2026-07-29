package com.example.myapplication.component

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRVCheckPoint
import com.example.myapplication.model.StepControlGRV

class GRVControlStepTemplate(stepControlGRV: StepControlGRV? = null, context: Context) {

    private val mContext = context
    private val mStepControlGRV = stepControlGRV

    private val mType = when (mStepControlGRV) {
        is StepControlGRV.Step0ControlGRV -> GRVControlStepEnum.STEP_0
        else -> GRVControlStepEnum.STEP_0
    }

    fun getItemCount(): Int = when (mType) {
        GRVControlStepEnum.STEP_0 -> 5
        else -> 0
    }

    fun getStepRecyclerItem(): List<ControlGRVCheckPoint> = when (mType) {
        GRVControlStepEnum.STEP_0 -> getStep0RecyclerItem(data = mStepControlGRV as StepControlGRV.Step0ControlGRV?)
        else -> getStep0RecyclerItem(data = mStepControlGRV as StepControlGRV.Step0ControlGRV?)
    }

    private fun getStep0RecyclerItem(data: StepControlGRV.Step0ControlGRV?): List<ControlGRVCheckPoint> =
        listOf(
            ControlGRVCheckPoint.EditableCheckPoint(title = mContext.getString(R.string.control_grv_checkpoint_report_number), name = (data?.reference ?: "").toString()),
            ControlGRVCheckPoint.EditableCheckPoint(title = mContext.getString(R.string.control_grv_checkpoint_customer_name), name = data?.customer ?: ""),
            ControlGRVCheckPoint.EditableCheckPoint(title = mContext.getString(R.string.control_grv_checkpoint_tank_category), name = data?.type ?: ""),
            ControlGRVCheckPoint.EditableCheckPoint(title = mContext.getString(R.string.control_grv_checkpoint_customer_serial_number), name = (data?.customerSerialNumber ?: "").toString()),
            ControlGRVCheckPoint.EditableCheckPoint(title = mContext.getString(R.string.control_grv_checkpoint_alorem_serial_number), name = (data?.serialNumberAlorem ?: "").toString())
        )
}