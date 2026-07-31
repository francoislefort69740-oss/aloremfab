package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository
import com.example.domain.repository.db.ControlGRVStepLocalRepository
import com.example.domain.utils.getEmptyControlGRVStep

class CreateControlGRVStepUseCase(private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository, private val controlGRVLocalRepository: ControlGRVLocalRepository) {
    suspend operator fun invoke(stepNumber: Int, reference: Int): ResultOf<ControlGRVStepBusiness> {
        return try {
            when (stepNumber) {
                0 -> {
                    if (controlGRVStepLocalRepository.checkIfControlGRVStepExist(reference, ControlGRVStepBusiness.ControlGRVStep0::class))  {
                        val result = controlGRVStepLocalRepository.updateLocalControlGRVStep(controlGRVStepBusiness = getEmptyControlGRVStep(stepNumber = stepNumber, reference = reference))
                        if (result) {
                            controlGRVLocalRepository.updateLocalControlGRV(controlGRVBusiness = controlGRVLocalRepository.getLocalControlGRVBySerialNumber(reference))
                            ResultOf.Success(getEmptyControlGRVStep(stepNumber = stepNumber, reference = reference) as ControlGRVStepBusiness.ControlGRVStep0)
                        } else ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)
                    }
                    else {
                        val result = controlGRVStepLocalRepository.createLocalControlGRVStep(controlGRVStepBusiness = getEmptyControlGRVStep(stepNumber = stepNumber, reference = reference))
                        if (result) {
                            ResultOf.Success(getEmptyControlGRVStep(stepNumber = stepNumber, reference = reference) as ControlGRVStepBusiness.ControlGRVStep0)
                        } else ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)
                    }
                }
                1 -> {
                    if (controlGRVStepLocalRepository.checkIfControlGRVStepExist(reference, ControlGRVStepBusiness.ControlGRVStep1::class))  {
                        val result = controlGRVStepLocalRepository.updateLocalControlGRVStep(controlGRVStepBusiness = getEmptyControlGRVStep(stepNumber = stepNumber, reference = reference))
                        if (result) {
                            ResultOf.Success(getEmptyControlGRVStep(stepNumber = stepNumber, reference = reference) as ControlGRVStepBusiness.ControlGRVStep1)
                        } else ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)
                    }
                    else {
                        val result = controlGRVStepLocalRepository.createLocalControlGRVStep(controlGRVStepBusiness = getEmptyControlGRVStep(stepNumber = stepNumber, reference = reference))
                        if (result) {
                            val control = controlGRVLocalRepository.getLocalControlGRVBySerialNumber(serialNumber = reference).apply { currentStep = stepNumber }
                            controlGRVLocalRepository.updateLocalControlGRV(controlGRVBusiness = control)
                            ResultOf.Success(getEmptyControlGRVStep(stepNumber = stepNumber, reference = reference) as ControlGRVStepBusiness.ControlGRVStep1)
                        } else ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)
                    }
                }
                else -> ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)
            }
        } catch (e: Exception) {
            ResultOf.Error(e)
        }
    }
}