package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository
import com.example.domain.repository.db.ControlGRVStepLocalRepository
import com.example.domain.utils.GRVControlStepEnum
import com.example.domain.utils.getKClass

class CheckSaveOrNextControlGRVUseCase(private val controlGRVStepUseCase: ControlGRVStepLocalRepository, private val controlGRVLocalRepository: ControlGRVLocalRepository) {
    suspend operator fun invoke(reference: Int?, currentStep: GRVControlStepEnum): ResultOf<Triple<Boolean, Boolean, Boolean>> {
        return try {
            if (reference == null) {
                return ResultOf.Error(ErrorBusiness.ControlGRVWrongSerialNumber)
            } else {
                val controlExist = controlGRVLocalRepository.checkIfControlGRVExist(reference)
                val type = getKClass(stepNumber = currentStep)
                val nextStep = GRVControlStepEnum.getStepNumber(currentStep) + 1
                val nextType = getKClass(stepNumber = GRVControlStepEnum.getStep(nextStep))
                val currentStepExist = controlGRVStepUseCase.checkIfControlGRVStepExist(reference, type = type)
                val nextStepExist = controlGRVStepUseCase.checkIfControlGRVStepExist(reference, type = nextType)
                return ResultOf.Success(Triple(controlExist, currentStepExist, nextStepExist))
            }
        } catch (e: Exception) {
            ResultOf.Error(ErrorBusiness.ControlGRVNotFound)
        }

    }
}