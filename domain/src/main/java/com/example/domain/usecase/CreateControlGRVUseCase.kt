package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository
import com.example.domain.repository.db.ControlGRVStepLocalRepository
import kotlin.reflect.KClass

class CreateControlGRVUseCase(private val controlGRVLocalRepository: ControlGRVLocalRepository, private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository) {
    suspend operator fun invoke(controlGRVBusiness: ControlGRVBusiness, controlGRVStepBusiness: ControlGRVStepBusiness): ResultOf<Pair<List<ControlGRVBusiness>, ControlGRVBusiness>> {
        return try {
            controlGRVBusiness.serialNumber?.let { serialNumber ->
                try {
                    if (!controlGRVLocalRepository.checkIfControlGRVExist(controlGRVId = controlGRVBusiness.serialNumber)) {

                        // CREATE FULL CONTROL GRC

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
                    reportNumber = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep0).reportNumber,
                    customer = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep0).customer,
                    customerSerialNumber = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep0).customerSerialNumber,
                    serialNumberAlorem = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep0).serialNumberAlorem,
                    type = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep0).type,
                    controlGRVForeignId = serialNumber
                )
            )
        is ControlGRVStepBusiness.ControlGRVStep1 ->
            controlGRVStepLocalRepository.createLocalControlGRVStep(
                ControlGRVStepBusiness.ControlGRVStep1(
                    reference = serialNumber,
                    fabricationPlateAdr = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep1).fabricationPlateAdr,
                    aloremPlate = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep1).aloremPlate,
                    bookletPouch = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep1).bookletPouch,
                    userManual = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep1).userManual,
                    instructionOfUse = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep1).instructionOfUse,
                    certificatesADR = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep1).certificatesADR,
                    groundingAdhesive = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep1).groundingAdhesive,
                    conformityCertificateMarking = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep1).conformityCertificateMarking,
                    controlGRVForeignId = serialNumber
                )
            )
    }

    suspend fun updateControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness, serialNumber: Int): Boolean = when (controlGRVStepBusiness) {
        is ControlGRVStepBusiness.ControlGRVStep0 ->
            controlGRVStepLocalRepository.updateLocalControlGRVStep(
                ControlGRVStepBusiness.ControlGRVStep0(
                    reference = serialNumber,
                    reportNumber = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep0).reportNumber,
                    customer = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep0).customer,
                    customerSerialNumber = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep0).customerSerialNumber,
                    serialNumberAlorem = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep0).serialNumberAlorem,
                    type = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep0).type,
                    controlGRVForeignId = serialNumber
                )
            )
        is ControlGRVStepBusiness.ControlGRVStep1 ->
            controlGRVStepLocalRepository.updateLocalControlGRVStep(
                ControlGRVStepBusiness.ControlGRVStep1(
                    reference = serialNumber,
                    fabricationPlateAdr = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep1).fabricationPlateAdr,
                    aloremPlate = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep1).aloremPlate,
                    bookletPouch = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep1).bookletPouch,
                    userManual = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep1).userManual,
                    instructionOfUse = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep1).instructionOfUse,
                    certificatesADR = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep1).certificatesADR,
                    groundingAdhesive = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep1).groundingAdhesive,
                    conformityCertificateMarking = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep1).conformityCertificateMarking,
                    controlGRVForeignId = serialNumber
                )
            )
    }


}