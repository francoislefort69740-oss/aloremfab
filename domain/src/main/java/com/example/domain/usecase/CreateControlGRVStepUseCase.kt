package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository
import com.example.domain.repository.db.ControlGRVStepLocalRepository
import com.example.domain.utils.GRVControlStepEnum
import com.example.domain.utils.getEmptyControlGRVStep

class CreateControlGRVStepUseCase(private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository, private val controlGRVLocalRepository: ControlGRVLocalRepository) {
    suspend operator fun invoke(step: ControlGRVStepBusiness, controlGRVBusiness: ControlGRVBusiness): ResultOf<ControlGRVStepBusiness> {

        return try {

            if (step is ControlGRVStepBusiness.ControlGRVStep0 &&
                !controlGRVLocalRepository.checkIfControlGRVExist(step.reference)
            ) {
                return ResultOf.Error(ErrorBusiness.ControlGRVNotFound)
            }

            val type = step::class

            val saved = if (controlGRVStepLocalRepository.checkIfControlGRVStepExist(controlGRVStepId = step.reference, type = type)) {
                controlGRVStepLocalRepository.updateLocalControlGRVStep(step)
            } else {
                controlGRVStepLocalRepository.createLocalControlGRVStep(step)
            }

            if (!saved) return ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)

            if (!step.isValid()) return ResultOf.Success(step)

            controlGRVBusiness.currentStep = when (step) {
                is ControlGRVStepBusiness.ControlGRVStep0 -> 1
                is ControlGRVStepBusiness.ControlGRVStep1 -> 2
                is ControlGRVStepBusiness.ControlGRVStep2 -> 3
                is ControlGRVStepBusiness.ControlGRVStep3 -> 4
                is ControlGRVStepBusiness.ControlGRVStep4 -> 5
                is ControlGRVStepBusiness.ControlGRVStep5 -> 6
            }

            val updated = controlGRVLocalRepository.updateLocalControlGRV(controlGRVBusiness)

            if (updated != 0) ResultOf.Success(step)
            else ResultOf.Error(ErrorBusiness.ControlGRVNotFound)

        } catch (e: Exception) {
            ResultOf.Error(e)
        }
    }
}