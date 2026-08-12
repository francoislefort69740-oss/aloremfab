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
        unauthorizedRepair = null,
        controlGRVForeignId = reference
    )
    else -> throw IllegalArgumentException("Invalid type")
}

fun getKClass(stepNumber: GRVControlStepEnum): KClass<out ControlGRVStepBusiness> = when(stepNumber) {
    GRVControlStepEnum.STEP_0 -> ControlGRVStepBusiness.ControlGRVStep0::class
    GRVControlStepEnum.STEP_1 -> ControlGRVStepBusiness.ControlGRVStep1::class
    GRVControlStepEnum.STEP_2 -> ControlGRVStepBusiness.ControlGRVStep2::class
    GRVControlStepEnum.STEP_3 -> ControlGRVStepBusiness.ControlGRVStep3::class
    else -> throw IllegalArgumentException("Invalid type")
}