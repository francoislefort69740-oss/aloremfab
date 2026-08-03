package com.example.myapplication.component

import android.content.Context
import com.example.domain.utils.GRVControlStepEnum
import com.example.myapplication.model.ControlGRVCheckPoint
import com.example.myapplication.model.StepControlGRV
import com.example.myapplication.utils.getStepRecyclerItemFunction

class GRVControlStepTemplate(stepControlGRV: StepControlGRV? = null, context: Context) {

    private val mContext = context
    private val mStepControlGRV = stepControlGRV

    private val mType = when (mStepControlGRV) {
        is StepControlGRV.Step0ControlGRV -> GRVControlStepEnum.STEP_0
        is StepControlGRV.Step1ControlGRV -> GRVControlStepEnum.STEP_1
        else -> GRVControlStepEnum.STEP_0
    }

    fun getStepRecyclerItem(): List<ControlGRVCheckPoint> = getStepRecyclerItemFunction(mStepControlGRV = mStepControlGRV, mContext = mContext)
}