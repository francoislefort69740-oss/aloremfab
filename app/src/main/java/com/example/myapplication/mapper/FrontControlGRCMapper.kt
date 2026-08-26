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
            marquePrincipale = controlGRVStepBusiness.marquePrincipale,
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
        is ControlGRVStepBusiness.ControlGRVStep3 -> StepControlGRV.Step3ControlGRV(
            reference = controlGRVStepBusiness.reference,
            bottomRetentionFace = controlGRVStepBusiness.bottomRetentionFace,
            bottomRetentionRight = controlGRVStepBusiness.bottomRetentionRight,
            bottomRetentionLeft = controlGRVStepBusiness.bottomRetentionLeft,
            bottomRetentionBehind = controlGRVStepBusiness.bottomRetentionBehind,
            upperRetention = controlGRVStepBusiness.upperRetention,
            liftingRings = controlGRVStepBusiness.liftingRings,
            forkliftPass = controlGRVStepBusiness.forkliftPass,
            dashboard = controlGRVStepBusiness.dashboard,
            unauthorizedRepair = controlGRVStepBusiness.unauthorizedRepair,
            controlGRVForeignId = controlGRVStepBusiness.controlGRVForeignId
        )
        is ControlGRVStepBusiness.ControlGRVStep4 -> StepControlGRV.Step4ControlGRV(
            reference = controlGRVStepBusiness.reference,
            internalNA = controlGRVStepBusiness.internalNA,
            internalOK = controlGRVStepBusiness.internalOK,
            internalClean = controlGRVStepBusiness.internalClean,
            internalObjectInside = controlGRVStepBusiness.internalObjectInside,
            internalPollution = controlGRVStepBusiness.internalPollution,
            controlGRVForeignId = controlGRVStepBusiness.controlGRVForeignId
        )
        is ControlGRVStepBusiness.ControlGRVStep5 -> StepControlGRV.Step5ControlGRV(
            reference = controlGRVStepBusiness.reference,
            epaisseurNA = controlGRVStepBusiness.epaisseurNA,
            epaisseurMinSideFront = controlGRVStepBusiness.epaisseurMinSideFront,
            epaisseurMinSideBack = controlGRVStepBusiness.epaisseurMinSideBack,
            epaisseurMinSideRight = controlGRVStepBusiness.epaisseurMinSideRight,
            epaisseurMinSideLeft = controlGRVStepBusiness.epaisseurMinSideLeft,
            epaisseurSideFrontResult1 = controlGRVStepBusiness.epaisseurSideFrontResult1,
            epaisseurSideFrontResult2 = controlGRVStepBusiness.epaisseurSideFrontResult2,
            epaisseurSideFrontResult3 = controlGRVStepBusiness.epaisseurSideFrontResult3,
            epaisseurSideFrontResult4 = controlGRVStepBusiness.epaisseurSideFrontResult4,
            epaisseurSideFrontResult5 = controlGRVStepBusiness.epaisseurSideFrontResult5,
            epaisseurSideBackResult1 = controlGRVStepBusiness.epaisseurSideBackResult1,
            epaisseurSideBackResult2 = controlGRVStepBusiness.epaisseurSideBackResult2,
            epaisseurSideBackResult3 = controlGRVStepBusiness.epaisseurSideBackResult3,
            epaisseurSideBackResult4 = controlGRVStepBusiness.epaisseurSideBackResult4,
            epaisseurSideBackResult5 = controlGRVStepBusiness.epaisseurSideBackResult5,
            epaisseurSideRightResult1 = controlGRVStepBusiness.epaisseurSideRightResult1,
            epaisseurSideLeftResult1 = controlGRVStepBusiness.epaisseurSideLeftResult1,
            epaisseurSideRightResult2 = controlGRVStepBusiness.epaisseurSideRightResult2,
            epaisseurSideLeftResult2 = controlGRVStepBusiness.epaisseurSideLeftResult2,
            epaisseurSideRightResult3 = controlGRVStepBusiness.epaisseurSideRightResult3,
            epaisseurSideLeftResult3 = controlGRVStepBusiness.epaisseurSideLeftResult3,
            epaisseurSideRightResult4 = controlGRVStepBusiness.epaisseurSideRightResult4,
            epaisseurSideLeftResult4 = controlGRVStepBusiness.epaisseurSideLeftResult4,
            epaisseurSideRightResult5 = controlGRVStepBusiness.epaisseurSideRightResult5,
            epaisseurSideLeftResult5 = controlGRVStepBusiness.epaisseurSideLeftResult5,
            controlGRVForeignId = controlGRVStepBusiness.controlGRVForeignId
        )
        is ControlGRVStepBusiness.ControlGRVStep6 -> StepControlGRV.Step6ControlGRV(
            reference = controlGRVStepBusiness.reference,
            etancheiteConforme1 = controlGRVStepBusiness.etancheiteOK1,
            etancheiteDate1 = controlGRVStepBusiness.etancheiteDate1,
            etancheiteBar1 = controlGRVStepBusiness.etancheiteBar1,
            etancheiteConforme2 = controlGRVStepBusiness.etancheiteOK2,
            etancheiteDate2 = controlGRVStepBusiness.etancheiteDate2,
            etancheiteBar2 = controlGRVStepBusiness.etancheiteBar2,
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
            marquePrincipale = stepControlGRV.marquePrincipale ?: "",
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
        is StepControlGRV.Step3ControlGRV -> ControlGRVStepBusiness.ControlGRVStep3(
            reference = stepControlGRV.reference ?: 0,
            bottomRetentionFace = stepControlGRV.bottomRetentionFace,
            bottomRetentionRight = stepControlGRV.bottomRetentionRight,
            bottomRetentionLeft = stepControlGRV.bottomRetentionLeft,
            bottomRetentionBehind = stepControlGRV.bottomRetentionBehind,
            upperRetention = stepControlGRV.upperRetention,
            liftingRings = stepControlGRV.liftingRings,
            forkliftPass = stepControlGRV.forkliftPass,
            dashboard = stepControlGRV.dashboard,
            unauthorizedRepair = stepControlGRV.unauthorizedRepair,
            controlGRVForeignId = stepControlGRV.controlGRVForeignId ?: 0
        )
        is StepControlGRV.Step4ControlGRV -> ControlGRVStepBusiness.ControlGRVStep4(
            reference = stepControlGRV.reference ?: 0,
            internalNA = stepControlGRV.internalNA,
            internalOK = stepControlGRV.internalOK,
            internalClean = stepControlGRV.internalClean,
            internalObjectInside = stepControlGRV.internalObjectInside,
            internalPollution = stepControlGRV.internalPollution,
            controlGRVForeignId = stepControlGRV.controlGRVForeignId ?: 0
        )
        is StepControlGRV.Step5ControlGRV -> ControlGRVStepBusiness.ControlGRVStep5(
            reference = stepControlGRV.reference ?: 0,
            epaisseurNA = stepControlGRV.epaisseurNA,
            epaisseurMinSideFront = stepControlGRV.epaisseurMinSideFront ?: 0,
            epaisseurMinSideBack = stepControlGRV.epaisseurMinSideBack ?: 0,
            epaisseurMinSideRight = stepControlGRV.epaisseurMinSideRight ?: 0,
            epaisseurMinSideLeft = stepControlGRV.epaisseurMinSideLeft ?: 0,
            epaisseurSideFrontResult1 = stepControlGRV.epaisseurSideFrontResult1 ?: 0,
            epaisseurSideFrontResult2 = stepControlGRV.epaisseurSideFrontResult2 ?: 0,
            epaisseurSideFrontResult3 = stepControlGRV.epaisseurSideFrontResult3 ?: 0,
            epaisseurSideFrontResult4 = stepControlGRV.epaisseurSideFrontResult4 ?: 0,
            epaisseurSideFrontResult5 = stepControlGRV.epaisseurSideFrontResult5 ?: 0,
            epaisseurSideBackResult1 = stepControlGRV.epaisseurSideBackResult1 ?: 0,
            epaisseurSideBackResult2 = stepControlGRV.epaisseurSideBackResult2 ?: 0,
            epaisseurSideBackResult3 = stepControlGRV.epaisseurSideBackResult3 ?: 0,
            epaisseurSideBackResult4 = stepControlGRV.epaisseurSideBackResult4 ?: 0,
            epaisseurSideBackResult5 = stepControlGRV.epaisseurSideBackResult5 ?: 0,
            epaisseurSideRightResult1 = stepControlGRV.epaisseurSideRightResult1 ?: 0,
            epaisseurSideLeftResult1 = stepControlGRV.epaisseurSideLeftResult1 ?: 0,
            epaisseurSideRightResult2 = stepControlGRV.epaisseurSideRightResult2 ?: 0,
            epaisseurSideLeftResult2 = stepControlGRV.epaisseurSideLeftResult2 ?: 0,
            epaisseurSideRightResult3 = stepControlGRV.epaisseurSideRightResult3 ?: 0,
            epaisseurSideLeftResult3 = stepControlGRV.epaisseurSideLeftResult3 ?: 0,
            epaisseurSideRightResult4 = stepControlGRV.epaisseurSideRightResult4 ?: 0,
            epaisseurSideLeftResult4 = stepControlGRV.epaisseurSideLeftResult4 ?: 0,
            epaisseurSideRightResult5 = stepControlGRV.epaisseurSideRightResult5 ?: 0,
            epaisseurSideLeftResult5 = stepControlGRV.epaisseurSideLeftResult5 ?: 0,
            controlGRVForeignId = stepControlGRV.controlGRVForeignId ?: 0
        )
        is StepControlGRV.Step6ControlGRV -> ControlGRVStepBusiness.ControlGRVStep6(
            reference = stepControlGRV.reference ?: 0,
            etancheiteOK1 = stepControlGRV.etancheiteConforme1,
            etancheiteDate1 = stepControlGRV.etancheiteDate1 ?: "",
            etancheiteBar1 = stepControlGRV.etancheiteBar1 ?: 0F,
            etancheiteOK2 = stepControlGRV.etancheiteConforme2,
            etancheiteDate2 = stepControlGRV.etancheiteDate2 ?: "",
            etancheiteBar2 = stepControlGRV.etancheiteBar2 ?: 0F,
            controlGRVForeignId = stepControlGRV.controlGRVForeignId ?: 0
        )
    }
}