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
    GRVControlStepEnum.STEP_2 -> StepControlGRV.Step2ControlGRV(
        reference = serialNumber!!,
        tare = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_tare, list = list) ?: 0,
        material = returnCheckPointForEditableString(context = context, resId = R.string.control_grv_checkpoint_material, list = list),
        capacity20 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_capacity_20, list = list) ?: 0,
        grossMass = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_gross_mass, list = list) ?: 0,
        fabricationDate = returnCheckPointForEditableString(context = context, resId = R.string.control_grv_checkpoint_fabrication_date, list = list),
        shellThickness = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_shell_thickness, list = list) ?: 0,
        pictogramStacking = returnCheckPointForCheckBox(context = context, resId = R.string.control_grv_checkpoint_pictogram_stacking, list = list),
        weightStacking = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_weight_stacking, list = list) ?: 0,
        controlGRVForeignId = serialNumber
    )
    GRVControlStepEnum.STEP_3 -> StepControlGRV.Step3ControlGRV(
        reference = serialNumber!!,
        bottomRetentionFace = returnCheckPointForFourState(context = context, resId = R.string.control_grv_checkpoint_bottom_retention_face, list = list) ?: 0,
        bottomRetentionRight = returnCheckPointForFourState(context = context, resId = R.string.control_grv_checkpoint_bottom_retention_right, list = list) ?: 0,
        bottomRetentionLeft = returnCheckPointForFourState(context = context, resId = R.string.control_grv_checkpoint_bottom_retention_left, list = list) ?: 0,
        bottomRetentionBehind = returnCheckPointForFourState(context = context, resId = R.string.control_grv_checkpoint_bottom_retention_behind, list = list) ?: 0,
        upperRetention = returnCheckPointForFourState(context = context, resId = R.string.control_grv_checkpoint_upper_retention, list = list) ?: 0,
        liftingRings = returnCheckPointForFourState(context = context, resId = R.string.control_grv_checkpoint_lifting_rings, list = list) ?: 0,
        forkliftPass = returnCheckPointForFourState(context = context, resId = R.string.control_grv_checkpoint_forklift_pass, list = list) ?: 0,
        dashboard = returnCheckPointForFourState(context = context, resId = R.string.control_grv_checkpoint_dashboard, list = list) ?: 0,
        unauthorizedRepair = returnCheckPointForSingleCheck(context = context, resId = R.string.control_grv_checkpoint_unauthorized_repair, list = list),
        controlGRVForeignId = serialNumber
    )
    GRVControlStepEnum.STEP_4 -> StepControlGRV.Step4ControlGRV(
        reference = serialNumber!!,
        internalNA = returnCheckPointForSingleCheck(context = context, resId = R.string.control_grv_checkpoint_internal_na, list = list),
        internalOK = returnCheckPointForCheckBox(context = context, resId = R.string.control_grv_checkpoint_internal_ok, list = list),
        internalClean = returnCheckPointForSingleCheck(context = context, resId = R.string.control_grv_checkpoint_internal_clean, list = list),
        internalObjectInside = returnCheckPointForSingleCheck(context = context, resId = R.string.control_grv_checkpoint_internal_object_inside, list = list),
        internalPollution = returnCheckPointForSingleCheck(context = context, resId = R.string.control_grv_checkpoint_internal_pollution, list = list),
        controlGRVForeignId = serialNumber
    )
    GRVControlStepEnum.STEP_5 -> StepControlGRV.Step5ControlGRV(
        reference = serialNumber!!,
        epaisseurNA = returnCheckPointForSingleCheck(context = context, resId = R.string.control_grv_checkpoint_epaisseur_na, list = list),
        epaisseurMinSideFront = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_min_side_front, list = list) ?: 0,
        epaisseurMinSideBack = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_min_side_back, list = list) ?: 0,
        epaisseurMinSideRight = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_min_side_right, list = list) ?: 0,
        epaisseurMinSideLeft = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_min_side_left, list = list) ?: 0,
        epaisseurSideFrontResult1 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_front_result_1, list = list) ?: 0,
        epaisseurSideFrontResult2 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_front_result_2, list = list) ?: 0,
        epaisseurSideFrontResult3 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_front_result_3, list = list) ?: 0,
        epaisseurSideFrontResult4 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_front_result_4, list = list) ?: 0,
        epaisseurSideFrontResult5 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_front_result_5, list = list) ?: 0,
        epaisseurSideBackResult1 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_back_result_1, list = list) ?: 0,
        epaisseurSideBackResult2 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_back_result_2, list = list) ?: 0,
        epaisseurSideBackResult3 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_back_result_3, list = list) ?: 0,
        epaisseurSideBackResult4 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_back_result_4, list = list) ?: 0,
        epaisseurSideBackResult5 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_back_result_5, list = list) ?: 0,
        epaisseurSideRightResult1 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_right_result_1, list = list) ?: 0,
        epaisseurSideRightResult2 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_right_result_2, list = list) ?: 0,
        epaisseurSideRightResult3 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_right_result_3, list = list) ?: 0,
        epaisseurSideRightResult4 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_right_result_4, list = list) ?: 0,
        epaisseurSideRightResult5 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_right_result_5, list = list) ?: 0,
        epaisseurSideLeftResult1 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_left_result_1, list = list) ?: 0,
        epaisseurSideLeftResult2 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_left_result_2, list = list) ?: 0,
        epaisseurSideLeftResult3 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_left_result_3, list = list) ?: 0,
        epaisseurSideLeftResult4 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_left_result_4, list = list) ?: 0,
        epaisseurSideLeftResult5 = returnCheckPointForEditableInt(context = context, resId = R.string.control_grv_checkpoint_epaisseur_side_left_result_5, list = list) ?: 0,
        controlGRVForeignId = serialNumber
    )
    GRVControlStepEnum.STEP_6 -> StepControlGRV.Step6ControlGRV(
        reference = serialNumber!!,
        etancheiteConforme1 = returnCheckPointForCheckBox(context = context, resId = R.string.control_grv_checkpoint_etancheite_conforme_1, list = list),
        etancheiteConforme2 = returnCheckPointForCheckBox(context = context, resId = R.string.control_grv_checkpoint_etancheite_conforme_2, list = list),
        etancheiteDate1 = returnCheckPointForEditableString(context = context, resId = R.string.control_grv_checkpoint_etancheite_date_1, list = list),
        etancheiteDate2 = returnCheckPointForEditableString(context = context, resId = R.string.control_grv_checkpoint_etancheite_date_2, list = list),
        etancheiteBar1 = returnCheckPointForEditableFloat(context = context, resId = R.string.control_grv_checkpoint_etancheite_bar_1, list = list) ?: 0f,
        etancheiteBar2 = returnCheckPointForEditableFloat(context = context, resId = R.string.control_grv_checkpoint_etancheite_bar_2, list = list) ?: 0f,
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
    GRVControlStepEnum.STEP_2 -> getStep2RecyclerItem(data = mStepControlGRV as StepControlGRV.Step2ControlGRV?, mContext = mContext)
    GRVControlStepEnum.STEP_3 -> getStep3RecyclerItem(data = mStepControlGRV as StepControlGRV.Step3ControlGRV?, mContext = mContext)
    GRVControlStepEnum.STEP_4 -> getStep4RecyclerItem(data = mStepControlGRV as StepControlGRV.Step4ControlGRV?, mContext = mContext)
    GRVControlStepEnum.STEP_5 -> getStep5RecyclerItem(data = mStepControlGRV as StepControlGRV.Step5ControlGRV?, mContext = mContext)
    GRVControlStepEnum.STEP_6 -> getStep6RecyclerItem(data = mStepControlGRV as StepControlGRV.Step6ControlGRV?, mContext = mContext)
}

fun getType(mStepControlGRV: StepControlGRV?): GRVControlStepEnum = when(mStepControlGRV) {
    is StepControlGRV.Step0ControlGRV -> GRVControlStepEnum.STEP_0
    is StepControlGRV.Step1ControlGRV -> GRVControlStepEnum.STEP_1
    is StepControlGRV.Step2ControlGRV -> GRVControlStepEnum.STEP_2
    is StepControlGRV.Step3ControlGRV -> GRVControlStepEnum.STEP_3
    is StepControlGRV.Step4ControlGRV -> GRVControlStepEnum.STEP_4
    is StepControlGRV.Step5ControlGRV -> GRVControlStepEnum.STEP_5
    is StepControlGRV.Step6ControlGRV -> GRVControlStepEnum.STEP_6
    else -> GRVControlStepEnum.STEP_0
}

fun getEmptyStep(stepEnum: GRVControlStepEnum): StepControlGRV = when(stepEnum) {
    GRVControlStepEnum.STEP_0 -> StepControlGRV.Step0ControlGRV()
    GRVControlStepEnum.STEP_1 -> StepControlGRV.Step1ControlGRV()
    GRVControlStepEnum.STEP_2 -> StepControlGRV.Step2ControlGRV()
    GRVControlStepEnum.STEP_3 -> StepControlGRV.Step3ControlGRV()
    GRVControlStepEnum.STEP_4 -> StepControlGRV.Step4ControlGRV()
    GRVControlStepEnum.STEP_5 -> StepControlGRV.Step5ControlGRV()
    GRVControlStepEnum.STEP_6 -> StepControlGRV.Step6ControlGRV()
}
