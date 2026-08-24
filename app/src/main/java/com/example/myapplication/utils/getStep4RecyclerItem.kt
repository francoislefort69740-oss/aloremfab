package com.example.myapplication.utils

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRVCheckPoint
import com.example.myapplication.model.StepControlGRV

fun getStep4RecyclerItem(data: StepControlGRV.Step4ControlGRV?, mContext: Context): List<ControlGRVCheckPoint> =
    listOf(
        ControlGRVCheckPoint.SingleCheckCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_internal_na),
            value = data?.internalNA ?: false,
        ),
        ControlGRVCheckPoint.CheckBoxCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_internal_ok),
            value = data?.internalOK,
            isChecked = data?.internalOK != null
        ),
        ControlGRVCheckPoint.SingleCheckCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_internal_clean),
            value = data?.internalClean ?: false,
        ),
        ControlGRVCheckPoint.SingleCheckCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_internal_object_inside),
            value = data?.internalObjectInside ?: false,
        ),
        ControlGRVCheckPoint.SingleCheckCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_internal_pollution),
            value = data?.internalPollution ?: false,
        )
    )