package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository
import com.example.domain.repository.db.ControlGRVStepLocalRepository
import com.example.domain.utils.GRVControlStepEnum

class CheckSaveOrNextControlGRVUseCase(private val controlGRVStepUseCase: ControlGRVStepLocalRepository, private val controlGRVLocalRepository: ControlGRVLocalRepository) {
    suspend operator fun invoke(reference: Int, currentStep: GRVControlStepEnum): ResultOf<Triple<Boolean, Boolean, Boolean>> {
        return try {
            val controlExist = controlGRVLocalRepository.checkIfControlGRVExist(reference)
            val type = when (currentStep) {
                GRVControlStepEnum.STEP_0 -> ControlGRVStepBusiness.ControlGRVStep0::class
                GRVControlStepEnum.STEP_1 -> ControlGRVStepBusiness.ControlGRVStep1::class
                GRVControlStepEnum.STEP_2 -> ControlGRVStepBusiness.ControlGRVStep2::class
                else -> throw IllegalArgumentException("Invalid type")
            }
            val nextStep = GRVControlStepEnum.getStepNumber(currentStep) +1
            val nextType = when (GRVControlStepEnum.getStep(nextStep)) {
                GRVControlStepEnum.STEP_0 -> ControlGRVStepBusiness.ControlGRVStep0::class
                GRVControlStepEnum.STEP_1 -> ControlGRVStepBusiness.ControlGRVStep1::class
                GRVControlStepEnum.STEP_2 -> ControlGRVStepBusiness.ControlGRVStep2::class
                else -> throw IllegalArgumentException("Invalid type")
            }
            val currentStepExist = controlGRVStepUseCase.checkIfControlGRVStepExist(reference, type = type)
            val nextStepExist = controlGRVStepUseCase.checkIfControlGRVStepExist(reference, type = nextType)
            return ResultOf.Success(Triple(controlExist, currentStepExist, nextStepExist))
        } catch (e: Exception) {
            ResultOf.Error(ErrorBusiness.ControlGRVNotFound)
        }

    }
}