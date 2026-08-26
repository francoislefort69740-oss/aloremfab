package com.example.myapplication.utils

import android.content.Context
import android.text.InputType
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRVCheckPoint
import com.example.myapplication.model.StepControlGRV

fun getStep0RecyclerItem(data: StepControlGRV.Step0ControlGRV?, mContext: Context): List<ControlGRVCheckPoint> =
    listOf(
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_report_number),
            value = data?.reference
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.reference == null || data.reference == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),

        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_customer_name),
            value = data?.customer.orEmpty()
        ),

        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_tank_category),
            value = data?.type.orEmpty()
        ),

        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_customer_serial_number),
            value = data?.customerSerialNumber
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            inputType = InputType.TYPE_CLASS_NUMBER
        ),

        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_alorem_serial_number),
            value = data?.serialNumberAlorem
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            inputType = InputType.TYPE_CLASS_NUMBER
        )
    )