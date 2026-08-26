package com.example.myapplication.utils

import android.content.Context
import android.text.InputType
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRVCheckPoint
import com.example.myapplication.model.StepControlGRV

fun getStep2RecyclerItem(data: StepControlGRV.Step2ControlGRV?, mContext: Context): List<ControlGRVCheckPoint> =
    listOf(
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_marque_principale),
            value = data?.marquePrincipale.orEmpty(),
        ),

        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_tare),
            value = data?.tare
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            inputType = InputType.TYPE_CLASS_NUMBER
        ),

        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_material),
            value = data?.material.orEmpty(),
        ),

        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_capacity_20),
            value = data?.capacity20
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            inputType = InputType.TYPE_CLASS_NUMBER
        ),

        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_gross_mass),
            value = data?.grossMass
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            inputType = InputType.TYPE_CLASS_NUMBER
        ),

        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_fabrication_date),
            value = data?.fabricationDate.orEmpty(),
        ),

        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_shell_thickness),
            value = data?.shellThickness
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            inputType = InputType.TYPE_CLASS_NUMBER
        ),

        ControlGRVCheckPoint.CheckBoxCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_pictogram_stacking),
            value = data?.pictogramStacking,
            isChecked = data?.pictogramStacking != null
        ),

        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_weight_stacking),
            value = data?.weightStacking
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            inputType = InputType.TYPE_CLASS_NUMBER
        )
    )