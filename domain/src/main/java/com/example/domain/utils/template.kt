package com.example.domain.utils

import com.example.domain.model.ControlGRVStepBusiness

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
    else -> throw IllegalArgumentException("Invalid type")
}