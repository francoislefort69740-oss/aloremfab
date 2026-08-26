package com.example.domain.utils

import com.example.domain.model.ControlGRVStepBusiness
import kotlin.reflect.KClass

// GET EMPTY CONTROL GRV STEP ( for CreateControlGRVStepUseCase )

fun getEmptyControlGRVStep(stepNumber: GRVControlStepEnum, reference: Int): ControlGRVStepBusiness = when (stepNumber) {
    GRVControlStepEnum.STEP_0 -> ControlGRVStepBusiness.ControlGRVStep0(
        reference = reference,
        reportNumber = 0,
        customer = "",
        customerSerialNumber = 0,
        serialNumberAlorem = 0,
        type = "",
        controlGRVForeignId = reference
    )
    GRVControlStepEnum.STEP_1 -> ControlGRVStepBusiness.ControlGRVStep1(
        reference = reference,
        fabricationPlateAdr = null,
        aloremPlate = null,
        bookletPouch = null,
        userManual = null,
        instructionOfUse = null,
        certificatesADR = null,
        groundingAdhesive = null,
        conformityCertificateMarking = null,
        controlGRVForeignId = reference
    )
    GRVControlStepEnum.STEP_2 -> ControlGRVStepBusiness.ControlGRVStep2(
        reference = reference,
        tare = 0,
        material = "",
        capacity20 = 0,
        grossMass = 0,
        fabricationDate = "",
        shellThickness = 0,
        pictogramStacking = null,
        weightStacking = 0,
        controlGRVForeignId = reference
    )
    GRVControlStepEnum.STEP_3 -> ControlGRVStepBusiness.ControlGRVStep3(
        reference = reference,
        bottomRetentionFace = null,
        bottomRetentionRight = null,
        bottomRetentionLeft = null,
        bottomRetentionBehind = null,
        upperRetention = null,
        liftingRings = null,
        forkliftPass = null,
        dashboard = null,
        unauthorizedRepair = false,
        controlGRVForeignId = reference
    )
    GRVControlStepEnum.STEP_4 -> ControlGRVStepBusiness.ControlGRVStep4(
        reference = reference,
        internalNA = false,
        internalOK = null,
        internalClean = false,
        internalObjectInside = false,
        internalPollution = false,
        controlGRVForeignId = reference
    )
    GRVControlStepEnum.STEP_5 -> ControlGRVStepBusiness.ControlGRVStep5(
        reference = reference,
        epaisseurNA = false,
        epaisseurMinSideFront = 0,
        epaisseurMinSideBack = 0,
        epaisseurMinSideRight = 0,
        epaisseurMinSideLeft = 0,
        epaisseurSideFrontResult1 = 0,
        epaisseurSideFrontResult2 = 0,
        epaisseurSideFrontResult3 = 0,
        epaisseurSideFrontResult4 = 0,
        epaisseurSideFrontResult5 = 0,
        epaisseurSideBackResult1 = 0,
        epaisseurSideBackResult2 = 0,
        epaisseurSideBackResult3 = 0,
        epaisseurSideBackResult4 = 0,
        epaisseurSideBackResult5 = 0,
        epaisseurSideRightResult1 = 0,
        epaisseurSideRightResult2 = 0,
        epaisseurSideRightResult3 = 0,
        epaisseurSideRightResult4 = 0,
        epaisseurSideRightResult5 = 0,
        epaisseurSideLeftResult1 = 0,
        epaisseurSideLeftResult2 = 0,
        epaisseurSideLeftResult3 = 0,
        epaisseurSideLeftResult4 = 0,
        epaisseurSideLeftResult5 = 0,
        controlGRVForeignId = reference
    )
    else -> throw IllegalArgumentException("Invalid type")
}

fun getKClass(stepNumber: GRVControlStepEnum): KClass<out ControlGRVStepBusiness> = when(stepNumber) {
    GRVControlStepEnum.STEP_0 -> ControlGRVStepBusiness.ControlGRVStep0::class
    GRVControlStepEnum.STEP_1 -> ControlGRVStepBusiness.ControlGRVStep1::class
    GRVControlStepEnum.STEP_2 -> ControlGRVStepBusiness.ControlGRVStep2::class
    GRVControlStepEnum.STEP_3 -> ControlGRVStepBusiness.ControlGRVStep3::class
    GRVControlStepEnum.STEP_4 -> ControlGRVStepBusiness.ControlGRVStep4::class
    GRVControlStepEnum.STEP_5 -> ControlGRVStepBusiness.ControlGRVStep5::class
    else -> throw IllegalArgumentException("Invalid type")
}