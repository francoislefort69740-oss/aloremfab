package com.example.domain.usecase

import android.util.Log
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
                            val stepResult = updateControlGRVStep(controlGRVStepBusiness, serialNumber)
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

    suspend fun createGRCControlStep(controlGRVStepBusiness: ControlGRVStepBusiness, serialNumber: Int): Boolean {
        return controlGRVStepLocalRepository.createLocalControlGRVStep0(
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
    }

    suspend fun updateControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness, serialNumber: Int): Boolean = when (controlGRVStepBusiness) {
        is ControlGRVStepBusiness.ControlGRVStep0 ->
            controlGRVStepLocalRepository.createLocalControlGRVStep0(
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
    }


}