package com.example.myapplication.utils

import android.content.Context
import android.text.InputType
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRVCheckPoint
import com.example.myapplication.model.StepControlGRV

fun getStep6RecyclerItem(data: StepControlGRV.Step6ControlGRV?, mContext: Context): List<ControlGRVCheckPoint> =
    listOf(
        ControlGRVCheckPoint.CheckBoxCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_etancheite_conforme_1),
            value = data?.etancheiteConforme1,
            isChecked = data?.etancheiteConforme1 != null
        ),

        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_etancheite_date_1),
            value = data?.etancheiteDate1.orEmpty()
        ),

        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_etancheite_bar_1),
            value = data?.etancheiteBar1
                ?.takeIf { it != 0F }
                ?.toString()
                .orEmpty(),
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        ),

        ControlGRVCheckPoint.CheckBoxCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_etancheite_conforme_2),
            value = data?.etancheiteConforme2,
            isChecked = data?.etancheiteConforme2 != null
        ),

        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_etancheite_date_2),
            value = data?.etancheiteDate2.orEmpty()
        ),

        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_etancheite_bar_2),
            value = data?.etancheiteBar2
                ?.takeIf { it != 0F }
                ?.toString()
                .orEmpty(),
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        )

    )