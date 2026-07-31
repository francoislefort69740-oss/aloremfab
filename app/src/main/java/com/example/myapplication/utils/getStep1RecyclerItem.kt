package com.example.myapplication.utils

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRVCheckPoint
import com.example.myapplication.model.StepControlGRV

fun getStep1RecyclerItem(data: StepControlGRV.Step1ControlGRV?, mContext: Context): List<ControlGRVCheckPoint> =
    listOf(
        ControlGRVCheckPoint.CheckBoxCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_plate_adr),
            value = data?.fabricationPlateAdr,
            isChecked = data?.fabricationPlateAdr != null
        ),

        ControlGRVCheckPoint.CheckBoxCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_alorem_plate),
            value = data?.aloremPlate,
            isChecked = data?.aloremPlate != null
        ),

        ControlGRVCheckPoint.CheckBoxCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_booklet_pouch),
            value = data?.bookletPouch,
            isChecked = data?.bookletPouch != null
        ),

        ControlGRVCheckPoint.CheckBoxCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_user_manual),
            value = data?.userManual,
            isChecked = data?.userManual != null
        ),

        ControlGRVCheckPoint.CheckBoxCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_instruction_of_use),
            value = data?.instructionOfUse,
            isChecked = data?.instructionOfUse != null
        ),

        ControlGRVCheckPoint.CheckBoxCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_certificates_adr),
            value = data?.certificatesADR,
            isChecked = data?.certificatesADR != null
        ),

        ControlGRVCheckPoint.CheckBoxCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_grounding_adhesive),
            value = data?.groundingAdhesive,
            isChecked = data?.groundingAdhesive != null
        ),

        ControlGRVCheckPoint.CheckBoxCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_conformity_certificate_marking),
            value = data?.conformityCertificateMarking,
            isChecked = data?.conformityCertificateMarking != null
        )
    )


