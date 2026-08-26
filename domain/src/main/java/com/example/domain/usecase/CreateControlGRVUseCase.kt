package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository
import com.example.domain.repository.db.ControlGRVStepLocalRepository

class CreateControlGRVUseCase(private val controlGRVLocalRepository: ControlGRVLocalRepository, private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository) {
    suspend operator fun invoke(controlGRVBusiness: ControlGRVBusiness, controlGRVStepBusiness: ControlGRVStepBusiness): ResultOf<Pair<List<ControlGRVBusiness>, ControlGRVBusiness>> {
        return try {
            controlGRVBusiness.serialNumber?.let { serialNumber ->
                try {
                    if (!controlGRVLocalRepository.checkIfControlGRVExist(controlGRVId = controlGRVBusiness.serialNumber)) {

                        // CREATE FULL CONTROL GRC
                        if (controlGRVStepBusiness is ControlGRVStepBusiness.ControlGRVStep0 && controlGRVStepBusiness.isValid()) controlGRVBusiness.currentStep = 1
                        val result = createGRCControl(controlGRVBusiness, serialNumber)

                        // Add Control STEP 0

                        if (controlGRVLocalRepository.checkIfControlGRVExist(controlGRVId = controlGRVBusiness.serialNumber)) {
                            val stepResult = createGRCControlStep(controlGRVStepBusiness, serialNumber)
                            if (result != 0 && stepResult) ResultOf.Success(Pair(controlGRVLocalRepository.getUnloaded(),controlGRVBusiness))
                            else ResultOf.Error(ErrorBusiness.ControlGRVNotFound)
                        } else {
                            ResultOf.Error(ErrorBusiness.ControlGRVNotFound)
                        }

                    } else {

                        // UPDATE EXISTING CONTROL GRC

                        val result = updateGRCControl(controlGRVBusiness, serialNumber)

                        if (controlGRVLocalRepository.checkIfControlGRVExist(controlGRVId = controlGRVBusiness.serialNumber)) {

                            val stepResult = if (controlGRVStepLocalRepository.checkIfControlGRVStepExist(controlGRVStepId = serialNumber, type = controlGRVStepBusiness::class)) {
                                updateControlGRVStep(controlGRVStepBusiness, serialNumber)
                            } else {
                                createGRCControlStep(controlGRVStepBusiness, serialNumber)
                            }

                            if (result != 0 && stepResult) ResultOf.Success(Pair(controlGRVLocalRepository.getUnloaded(),controlGRVBusiness))
                            else ResultOf.Error(ErrorBusiness.ControlGRVNotFound)
                        } else {
                            ResultOf.Error(ErrorBusiness.ControlGRVNotFound)
                        }

                    }

                } catch (e: Exception){
                    ResultOf.Error(ErrorBusiness.ControlGRVNotFound)
                }

            } ?: ResultOf.Error(ErrorBusiness.ControlGRVWrongSerialNumber)

        } catch (e: Exception) {
            ResultOf.Error(ErrorBusiness.ControlGRVNotFound)
        }
    }

    suspend fun createGRCControl(controlGRVBusiness: ControlGRVBusiness, serialNumber: Int): Int {
        return controlGRVLocalRepository.createLocalControlGRV(
            ControlGRVBusiness(
                uid = serialNumber,
                serialNumber = serialNumber,
                currentStep = controlGRVBusiness.currentStep,
                currentlyGoingOn = true,
                loaded = false
            )
        )
    }

    suspend fun updateGRCControl(controlGRVBusiness: ControlGRVBusiness, serialNumber: Int): Int {
        return controlGRVLocalRepository.updateLocalControlGRV(
            ControlGRVBusiness(
                uid = serialNumber,
                serialNumber = serialNumber,
                currentStep = controlGRVBusiness.currentStep,
                currentlyGoingOn = true,
                loaded = false
            )
        )
    }

    suspend fun createGRCControlStep(controlGRVStepBusiness: ControlGRVStepBusiness, serialNumber: Int): Boolean = when (controlGRVStepBusiness) {
        is ControlGRVStepBusiness.ControlGRVStep0 ->
            controlGRVStepLocalRepository.createLocalControlGRVStep(
                ControlGRVStepBusiness.ControlGRVStep0(
                    reference = serialNumber,
                    reportNumber = controlGRVStepBusiness.reportNumber,
                    customer = controlGRVStepBusiness.customer,
                    customerSerialNumber = controlGRVStepBusiness.customerSerialNumber,
                    serialNumberAlorem = controlGRVStepBusiness.serialNumberAlorem,
                    type = controlGRVStepBusiness.type,
                    controlGRVForeignId = serialNumber
                )
            )
        is ControlGRVStepBusiness.ControlGRVStep1 ->
            controlGRVStepLocalRepository.createLocalControlGRVStep(
                ControlGRVStepBusiness.ControlGRVStep1(
                    reference = serialNumber,
                    fabricationPlateAdr = controlGRVStepBusiness.fabricationPlateAdr,
                    aloremPlate = controlGRVStepBusiness.aloremPlate,
                    bookletPouch = controlGRVStepBusiness.bookletPouch,
                    userManual = controlGRVStepBusiness.userManual,
                    instructionOfUse = controlGRVStepBusiness.instructionOfUse,
                    certificatesADR = controlGRVStepBusiness.certificatesADR,
                    groundingAdhesive = controlGRVStepBusiness.groundingAdhesive,
                    conformityCertificateMarking = controlGRVStepBusiness.conformityCertificateMarking,
                    controlGRVForeignId = serialNumber
                )
            )
        is ControlGRVStepBusiness.ControlGRVStep2 ->
            controlGRVStepLocalRepository.createLocalControlGRVStep(
                ControlGRVStepBusiness.ControlGRVStep2(
                    reference = serialNumber,
                    tare = controlGRVStepBusiness.tare,
                    material = controlGRVStepBusiness.material,
                    capacity20 = controlGRVStepBusiness.capacity20,
                    grossMass = controlGRVStepBusiness.grossMass,
                    fabricationDate = controlGRVStepBusiness.fabricationDate,
                    shellThickness = controlGRVStepBusiness.shellThickness,
                    pictogramStacking = controlGRVStepBusiness.pictogramStacking,
                    weightStacking = controlGRVStepBusiness.weightStacking,
                    controlGRVForeignId = serialNumber
                )
            )
        is ControlGRVStepBusiness.ControlGRVStep3 ->
            controlGRVStepLocalRepository.createLocalControlGRVStep(
                ControlGRVStepBusiness.ControlGRVStep3(
                    reference = serialNumber,
                    bottomRetentionFace = controlGRVStepBusiness.bottomRetentionFace,
                    bottomRetentionRight = controlGRVStepBusiness.bottomRetentionRight,
                    bottomRetentionLeft = controlGRVStepBusiness.bottomRetentionLeft,
                    bottomRetentionBehind = controlGRVStepBusiness.bottomRetentionBehind,
                    upperRetention = controlGRVStepBusiness.upperRetention,
                    liftingRings = controlGRVStepBusiness.liftingRings,
                    forkliftPass = controlGRVStepBusiness.forkliftPass,
                    dashboard = controlGRVStepBusiness.dashboard,
                    unauthorizedRepair = controlGRVStepBusiness.unauthorizedRepair,
                    controlGRVForeignId = serialNumber
                )
            )
        is ControlGRVStepBusiness.ControlGRVStep4 ->
            controlGRVStepLocalRepository.createLocalControlGRVStep(
                ControlGRVStepBusiness.ControlGRVStep4(
                    reference = serialNumber,
                    internalNA = controlGRVStepBusiness.internalNA,
                    internalOK = controlGRVStepBusiness.internalOK,
                    internalClean = controlGRVStepBusiness.internalClean,
                    internalObjectInside = controlGRVStepBusiness.internalObjectInside,
                    internalPollution = controlGRVStepBusiness.internalPollution,
                    controlGRVForeignId = serialNumber
                )
            )
        is ControlGRVStepBusiness.ControlGRVStep5 ->
            controlGRVStepLocalRepository.createLocalControlGRVStep(
                ControlGRVStepBusiness.ControlGRVStep5(
                    reference = serialNumber,
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
                    epaisseurSideRightResult2 = controlGRVStepBusiness.epaisseurSideRightResult2,
                    epaisseurSideRightResult3 = controlGRVStepBusiness.epaisseurSideRightResult3,
                    epaisseurSideRightResult4 = controlGRVStepBusiness.epaisseurSideRightResult4,
                    epaisseurSideRightResult5 = controlGRVStepBusiness.epaisseurSideRightResult5,
                    epaisseurSideLeftResult1 = controlGRVStepBusiness.epaisseurSideLeftResult1,
                    epaisseurSideLeftResult2 = controlGRVStepBusiness.epaisseurSideLeftResult2,
                    epaisseurSideLeftResult3 = controlGRVStepBusiness.epaisseurSideLeftResult3,
                    epaisseurSideLeftResult4 = controlGRVStepBusiness.epaisseurSideLeftResult4,
                    epaisseurSideLeftResult5 = controlGRVStepBusiness.epaisseurSideLeftResult5,
                    controlGRVForeignId = serialNumber
                )
            )
    }

    suspend fun updateControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness, serialNumber: Int): Boolean = when (controlGRVStepBusiness) {
        is ControlGRVStepBusiness.ControlGRVStep0 ->
            controlGRVStepLocalRepository.updateLocalControlGRVStep(
                ControlGRVStepBusiness.ControlGRVStep0(
                    reference = serialNumber,
                    reportNumber = controlGRVStepBusiness.reportNumber,
                    customer = controlGRVStepBusiness.customer,
                    customerSerialNumber = controlGRVStepBusiness.customerSerialNumber,
                    serialNumberAlorem = controlGRVStepBusiness.serialNumberAlorem,
                    type = controlGRVStepBusiness.type,
                    controlGRVForeignId = serialNumber
                )
            )
        is ControlGRVStepBusiness.ControlGRVStep1 ->
            controlGRVStepLocalRepository.updateLocalControlGRVStep(
                ControlGRVStepBusiness.ControlGRVStep1(
                    reference = serialNumber,
                    fabricationPlateAdr = controlGRVStepBusiness.fabricationPlateAdr,
                    aloremPlate = controlGRVStepBusiness.aloremPlate,
                    bookletPouch = controlGRVStepBusiness.bookletPouch,
                    userManual = controlGRVStepBusiness.userManual,
                    instructionOfUse = controlGRVStepBusiness.instructionOfUse,
                    certificatesADR = controlGRVStepBusiness.certificatesADR,
                    groundingAdhesive = controlGRVStepBusiness.groundingAdhesive,
                    conformityCertificateMarking = controlGRVStepBusiness.conformityCertificateMarking,
                    controlGRVForeignId = serialNumber
                )
            )
        is ControlGRVStepBusiness.ControlGRVStep2 ->
            controlGRVStepLocalRepository.updateLocalControlGRVStep(
                ControlGRVStepBusiness.ControlGRVStep2(
                    reference = serialNumber,
                    tare = controlGRVStepBusiness.tare,
                    material = controlGRVStepBusiness.material,
                    capacity20 = controlGRVStepBusiness.capacity20,
                    grossMass = controlGRVStepBusiness.grossMass,
                    fabricationDate = controlGRVStepBusiness.fabricationDate,
                    shellThickness = controlGRVStepBusiness.shellThickness,
                    pictogramStacking = controlGRVStepBusiness.pictogramStacking,
                    weightStacking = controlGRVStepBusiness.weightStacking,
                    controlGRVForeignId = serialNumber
                )
            )
        is ControlGRVStepBusiness.ControlGRVStep3 ->
            controlGRVStepLocalRepository.updateLocalControlGRVStep(
                ControlGRVStepBusiness.ControlGRVStep3(
                    reference = serialNumber,
                    bottomRetentionFace = controlGRVStepBusiness.bottomRetentionFace,
                    bottomRetentionRight = controlGRVStepBusiness.bottomRetentionRight,
                    bottomRetentionLeft = controlGRVStepBusiness.bottomRetentionLeft,
                    bottomRetentionBehind = controlGRVStepBusiness.bottomRetentionBehind,
                    upperRetention = controlGRVStepBusiness.upperRetention,
                    liftingRings = controlGRVStepBusiness.liftingRings,
                    forkliftPass = controlGRVStepBusiness.forkliftPass,
                    dashboard = controlGRVStepBusiness.dashboard,
                    unauthorizedRepair = controlGRVStepBusiness.unauthorizedRepair,
                    controlGRVForeignId = serialNumber
                )
            )
        is ControlGRVStepBusiness.ControlGRVStep4 ->
            controlGRVStepLocalRepository.updateLocalControlGRVStep(
                ControlGRVStepBusiness.ControlGRVStep4(
                    reference = serialNumber,
                    internalNA = controlGRVStepBusiness.internalNA,
                    internalOK = controlGRVStepBusiness.internalOK,
                    internalClean = controlGRVStepBusiness.internalClean,
                    internalObjectInside = controlGRVStepBusiness.internalObjectInside,
                    internalPollution = controlGRVStepBusiness.internalPollution,
                    controlGRVForeignId = serialNumber
                )
            )
        is ControlGRVStepBusiness.ControlGRVStep5 ->
            controlGRVStepLocalRepository.updateLocalControlGRVStep(
                ControlGRVStepBusiness.ControlGRVStep5(
                    reference = serialNumber,
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
                    epaisseurSideRightResult2 = controlGRVStepBusiness.epaisseurSideRightResult2,
                    epaisseurSideRightResult3 = controlGRVStepBusiness.epaisseurSideRightResult3,
                    epaisseurSideRightResult4 = controlGRVStepBusiness.epaisseurSideRightResult4,
                    epaisseurSideRightResult5 = controlGRVStepBusiness.epaisseurSideRightResult5,
                    epaisseurSideLeftResult1 = controlGRVStepBusiness.epaisseurSideLeftResult1,
                    epaisseurSideLeftResult2 = controlGRVStepBusiness.epaisseurSideLeftResult2,
                    epaisseurSideLeftResult3 = controlGRVStepBusiness.epaisseurSideLeftResult3,
                    epaisseurSideLeftResult4 = controlGRVStepBusiness.epaisseurSideLeftResult4,
                    epaisseurSideLeftResult5 = controlGRVStepBusiness.epaisseurSideLeftResult5,
                    controlGRVForeignId = serialNumber
                )
            )
    }


}
