package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVStepLocalRepository
import com.example.domain.utils.GRVControlStepEnum

class GetControlGRVStepUseCase(private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository) {
    suspend operator fun invoke(reference: Int, stepNumber: GRVControlStepEnum): ResultOf<ControlGRVStepBusiness> {
        return try {
            when (stepNumber) {
                GRVControlStepEnum.STEP_0 -> ResultOf.Success(controlGRVStepLocalRepository.getLocalControlGRVStepByReference(
                    reference = reference,
                    type = ControlGRVStepBusiness.ControlGRVStep0::class)
                )
                GRVControlStepEnum.STEP_1 -> ResultOf.Success(controlGRVStepLocalRepository.getLocalControlGRVStepByReference(
                    reference = reference,
                    type = ControlGRVStepBusiness.ControlGRVStep1::class)
                )
                GRVControlStepEnum.STEP_2 -> ResultOf.Success(controlGRVStepLocalRepository.getLocalControlGRVStepByReference(
                    reference = reference,
                    type = ControlGRVStepBusiness.ControlGRVStep2::class)
                )
                else -> ResultOf.Error(ErrorBusiness.ControlGRVStepNotInitialized)
            }

        } catch (e: Exception) {
            ResultOf.Error(ErrorBusiness.ControlGRVStepNotInitialized)
        }
    }
}