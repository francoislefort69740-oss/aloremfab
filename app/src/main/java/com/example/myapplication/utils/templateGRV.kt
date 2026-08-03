package com.example.myapplication.utils

import android.content.Context
import com.example.myapplication.R
import com.example.domain.utils.GRVControlStepEnum
import com.example.myapplication.model.ControlGRVCheckPoint
import com.example.myapplication.model.StepControlGRV



// *** ***************************************************************************************************** ***
// *                                                                                                           *
// *          TRANSLATION CONTROL STEP TO CONTROL GRV ( in GRVControlProcess )                                 *
// *                                                                                                           *
// *** ***************************************************************************************************** ***




fun grvControlProcess(currentStep: GRVControlStepEnum, list : List<ControlGRVCheckPoint>, context: Context, serialNumber: Int?): StepControlGRV = when(currentStep) {
    GRVControlStepEnum.STEP_0 -> StepControlGRV.Step0ControlGRV(
        reference = serialNumber,
        reportNumber = serialNumber,
        customer = returnCheckPointForEditableString(context = context, resId = R.string.control_grv_checkpoint_customer_name, list = list),
        customerSerialNumber = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_customer_serial_number, list = list) ?: 0,
        serialNumberAlorem = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_alorem_serial_number, list = list) ?: 0,
        type = returnCheckPointForEditableString(context = context, resId = R.string.control_grv_checkpoint_tank_category, list = list),
        controlGRVForeignId = serialNumber
    )
    GRVControlStepEnum.STEP_1-> StepControlGRV.Step1ControlGRV(
        reference = serialNumber!!,
        fabricationPlateAdr = returnCheckPointForCheckBox(context = context, resId = R.string.control_grv_checkpoint_plate_adr, list = list),
        aloremPlate = returnCheckPointForCheckBox(context = context, resId = R.string.control_grv_checkpoint_alorem_plate, list = list),
        bookletPouch = returnCheckPointForCheckBox(context = context, resId = R.string.control_grv_checkpoint_booklet_pouch, list = list),
        userManual = returnCheckPointForCheckBox(context = context, resId = R.string.control_grv_checkpoint_user_manual, list = list),
        instructionOfUse = returnCheckPointForCheckBox(context = context, resId = R.string.control_grv_checkpoint_instruction_of_use, list = list),
        certificatesADR = returnCheckPointForCheckBox(context = context, resId = R.string.control_grv_checkpoint_certificates_adr, list = list),
        groundingAdhesive = returnCheckPointForCheckBox(context = context, resId = R.string.control_grv_checkpoint_grounding_adhesive, list = list),
        conformityCertificateMarking = returnCheckPointForCheckBox(context = context, resId = R.string.control_grv_checkpoint_conformity_certificate_marking, list = list),
        controlGRVForeignId = serialNumber
    )
    else -> throw IllegalArgumentException("Invalid type")
}




// *** ***************************************************************************************************** ***
// *                                                                                                           *
// *                      GET STEP RECYCLER ITEM ( in GRVControlStepTemplate )                                 *
// *                                                                                                           *
// *** ***************************************************************************************************** ***




fun getStepRecyclerItemFunction(mStepControlGRV: StepControlGRV?, mContext: Context): List<ControlGRVCheckPoint> = when (getType(mStepControlGRV)) {
    GRVControlStepEnum.STEP_0 -> getStep0RecyclerItem(data = mStepControlGRV as StepControlGRV.Step0ControlGRV?, mContext = mContext)
    GRVControlStepEnum.STEP_1 -> getStep1RecyclerItem(data = mStepControlGRV as StepControlGRV.Step1ControlGRV?, mContext = mContext)
    else -> getStep0RecyclerItem(data = mStepControlGRV as StepControlGRV.Step0ControlGRV?, mContext = mContext)
}

fun getType(mStepControlGRV: StepControlGRV?): GRVControlStepEnum = when(mStepControlGRV) {
    is StepControlGRV.Step0ControlGRV -> GRVControlStepEnum.STEP_0
    is StepControlGRV.Step1ControlGRV -> GRVControlStepEnum.STEP_1
    else -> GRVControlStepEnum.STEP_0
}
