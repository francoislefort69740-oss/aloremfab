package com.example.myapplication.mapper

import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.utils.GRVControlStepEnum
import com.example.myapplication.model.ControlGRV
import com.example.myapplication.model.StepControlGRV

object FrontControlGRCMapper {
    fun allControlGRVBusinessToFront(controlGRVBusiness: List<ControlGRVBusiness>): List<ControlGRV> {
        val result = mutableListOf<ControlGRV>()
        controlGRVBusiness.forEach {
            result.add(controlGRVBusinessToFront(it))
        }
        return result
    }

    fun controlGRVBusinessToFront(controlGRVBusiness: ControlGRVBusiness): ControlGRV = ControlGRV(
        uid = controlGRVBusiness.uid,
        serialNumber = controlGRVBusiness.serialNumber,
        loaded = controlGRVBusiness.loaded,
        currentStep = GRVControlStepEnum.getStep(controlGRVBusiness.currentStep)
    )

    fun controlGRVFrontToBusiness(controlGRV: ControlGRV): ControlGRVBusiness {
        val business = ControlGRVBusiness(
            uid = controlGRV.uid,
            serialNumber = controlGRV.serialNumber,
            currentStep = controlGRV.currentStep.stepNumber,
            currentlyGoingOn = true,
            loaded = controlGRV.loaded
        )
        return business
    }

    fun controlGRVStepBusinessToFront(controlGRVStepBusiness: ControlGRVStepBusiness): StepControlGRV = when(controlGRVStepBusiness) {
        is ControlGRVStepBusiness.ControlGRVStep0 -> StepControlGRV.Step0ControlGRV(
            reference = controlGRVStepBusiness.reference,
            reportNumber = controlGRVStepBusiness.reportNumber,
            customer = controlGRVStepBusiness.customer,
            customerSerialNumber = controlGRVStepBusiness.customerSerialNumber,
            serialNumberAlorem = controlGRVStepBusiness.serialNumberAlorem,
            type = controlGRVStepBusiness.type,
            controlGRVForeignId = controlGRVStepBusiness.controlGRVForeignId
        )
        is ControlGRVStepBusiness.ControlGRVStep1 -> StepControlGRV.Step1ControlGRV(
            reference = controlGRVStepBusiness.reference,
            fabricationPlateAdr = controlGRVStepBusiness.fabricationPlateAdr,
            aloremPlate = controlGRVStepBusiness.aloremPlate,
            bookletPouch = controlGRVStepBusiness.bookletPouch,
            userManual = controlGRVStepBusiness.userManual,
            instructionOfUse = controlGRVStepBusiness.instructionOfUse,
            certificatesADR = controlGRVStepBusiness.certificatesADR,
            groundingAdhesive = controlGRVStepBusiness.groundingAdhesive,
            conformityCertificateMarking = controlGRVStepBusiness.conformityCertificateMarking,
            controlGRVForeignId = controlGRVStepBusiness.controlGRVForeignId
        )
        is ControlGRVStepBusiness.ControlGRVStep2 -> StepControlGRV.Step2ControlGRV(
            reference = controlGRVStepBusiness.reference,
            tare = controlGRVStepBusiness.tare,
            material = controlGRVStepBusiness.material,
            capacity20 = controlGRVStepBusiness.capacity20,
            grossMass = controlGRVStepBusiness.grossMass,
            fabricationDate = controlGRVStepBusiness.fabricationDate,
            shellThickness = controlGRVStepBusiness.shellThickness,
            pictogramStacking = controlGRVStepBusiness.pictogramStacking,
            weightStacking = controlGRVStepBusiness.weightStacking,
            controlGRVForeignId = controlGRVStepBusiness.controlGRVForeignId
        )
    }

    fun controlGRVStepFrontToBusiness(stepControlGRV: StepControlGRV): ControlGRVStepBusiness = when(stepControlGRV) {
        is StepControlGRV.Step0ControlGRV -> ControlGRVStepBusiness.ControlGRVStep0(
            reference = stepControlGRV.reference ?: 0,
            reportNumber = stepControlGRV.reportNumber ?: 0,
            customer = stepControlGRV.customer ?: "",
            customerSerialNumber = stepControlGRV.customerSerialNumber ?: 0,
            serialNumberAlorem = stepControlGRV.serialNumberAlorem ?: 0,
            type = stepControlGRV.type ?: "",
            controlGRVForeignId = stepControlGRV.reference ?: 0
        )
        is StepControlGRV.Step1ControlGRV -> ControlGRVStepBusiness.ControlGRVStep1(
            reference = stepControlGRV.reference ?: 0,
            fabricationPlateAdr = stepControlGRV.fabricationPlateAdr,
            aloremPlate = stepControlGRV.aloremPlate,
            bookletPouch = stepControlGRV.bookletPouch,
            userManual = stepControlGRV.userManual,
            instructionOfUse = stepControlGRV.instructionOfUse,
            certificatesADR = stepControlGRV.certificatesADR,
            groundingAdhesive = stepControlGRV.groundingAdhesive,
            conformityCertificateMarking = stepControlGRV.conformityCertificateMarking,
            controlGRVForeignId = stepControlGRV.controlGRVForeignId ?: 0
        )
        is StepControlGRV.Step2ControlGRV -> ControlGRVStepBusiness.ControlGRVStep2(
            reference = stepControlGRV.reference ?: 0,
            tare = stepControlGRV.tare ?: 0,
            material = stepControlGRV.material ?: "",
            capacity20 = stepControlGRV.capacity20 ?: 0,
            grossMass = stepControlGRV.grossMass ?: 0,
            fabricationDate = stepControlGRV.fabricationDate ?: "",
            shellThickness = stepControlGRV.shellThickness ?: 0,
            pictogramStacking = stepControlGRV.pictogramStacking,
            weightStacking = stepControlGRV.weightStacking ?: 0,
            controlGRVForeignId = stepControlGRV.controlGRVForeignId ?: 0
        )
    }
}