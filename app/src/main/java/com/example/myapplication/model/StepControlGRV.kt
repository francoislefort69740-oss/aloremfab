package com.example.myapplication.model

import androidx.annotation.StringRes
import com.example.myapplication.R

sealed class StepControlGRV{

    @get: StringRes
    abstract val title: Int

    data class Step0ControlGRV(
        override val title: Int = R.string.control_grv_checkpoint_step0_title,
        var reference: Int? = null,
        var reportNumber: Int? = null,
        var customer: String? = null,
        var customerSerialNumber: Int? = null,
        var serialNumberAlorem: Int? = null,
        var type: String? = null,
        var controlGRVForeignId: Int? = null
    ) : StepControlGRV()

    data class Step1ControlGRV(
        override val title: Int = R.string.control_grv_checkpoint_step1_title,
        val reference: Int? = null,
        var fabricationPlateAdr: Boolean? = null,
        var aloremPlate: Boolean? = null,
        var bookletPouch: Boolean? = null,
        var userManual: Boolean? = null,
        var instructionOfUse: Boolean? = null,
        var certificatesADR: Boolean? = null,
        var groundingAdhesive: Boolean? = null,
        var conformityCertificateMarking: Boolean? = null,
        var controlGRVForeignId: Int? = null
    ) : StepControlGRV()

    data class Step2ControlGRV(
        override val title: Int = R.string.control_grv_checkpoint_step2_title,
        val reference: Int? = null,
        var tare: Int? = null,
        var material: String? = null,
        var capacity20: Int? = null,
        var grossMass: Int? = null,
        var fabricationDate: String? = null,
        var shellThickness: Int? = null,
        var pictogramStacking: Boolean? = null,
        var weightStacking: Int? = null,
        var controlGRVForeignId: Int? = null
    ) : StepControlGRV()

    data class Step3ControlGRV(
        override val title: Int = R.string.control_grv_checkpoint_step3_title,
        val reference: Int? = null,
        var bottomRetentionFace: Int? = null,
        var bottomRetentionRight: Int? = null,
        var bottomRetentionLeft: Int? = null,
        var bottomRetentionBehind: Int? = null,
        var upperRetention: Int? = null,
        var liftingRings: Int? = null,
        var forkliftPass: Int? = null,
        var dashboard: Int? = null,
        var unauthorizedRepair: Boolean = false,
        var controlGRVForeignId: Int? = null
    ) : StepControlGRV()

    data class Step4ControlGRV(
        override val title: Int = R.string.control_grv_checkpoint_step4_title,
        val reference: Int? = null,
        var internalNA: Boolean = false,
        var internalOK: Boolean? = null,
        var internalClean: Boolean = false,
        var internalObjectInside: Boolean = false,
        var internalPollution: Boolean = false,
        var controlGRVForeignId: Int? = null
    ) : StepControlGRV()

    data class Step5ControlGRV(
        override val title: Int = R.string.control_grv_checkpoint_step5_title,
        val reference: Int? = null,
        var epaisseurNA: Boolean = false,
        var epaisseurMinSideFront: Int? = null,
        var epaisseurMinSideBack: Int? = null,
        var epaisseurMinSideRight: Int? = null,
        var epaisseurMinSideLeft: Int? = null,
        var epaisseurSideFrontResult1: Int? = null,
        var epaisseurSideFrontResult2: Int? = null,
        var epaisseurSideFrontResult3: Int? = null,
        var epaisseurSideFrontResult4: Int? = null,
        var epaisseurSideFrontResult5: Int? = null,
        var epaisseurSideBackResult1: Int? = null,
        var epaisseurSideBackResult2: Int? = null,
        var epaisseurSideBackResult3: Int? = null,
        var epaisseurSideBackResult4: Int? = null,
        var epaisseurSideBackResult5: Int? = null,
        var epaisseurSideRightResult1: Int? = null,
        var epaisseurSideRightResult2: Int? = null,
        var epaisseurSideRightResult3: Int? = null,
        var epaisseurSideRightResult4: Int? = null,
        var epaisseurSideRightResult5: Int? = null,
        var epaisseurSideLeftResult1: Int? = null,
        var epaisseurSideLeftResult2: Int? = null,
        var epaisseurSideLeftResult3: Int? = null,
        var epaisseurSideLeftResult4: Int? = null,
        var epaisseurSideLeftResult5: Int? = null,
        var controlGRVForeignId: Int? = null,
    ) : StepControlGRV()

}
