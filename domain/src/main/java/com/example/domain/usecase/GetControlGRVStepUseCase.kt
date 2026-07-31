package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVStepLocalRepository

class GetControlGRVStepUseCase(private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository) {
    suspend operator fun invoke(reference: Int, stepNumber: Int): ResultOf<ControlGRVStepBusiness> {
        return try {
            when (stepNumber) {
                0 -> ResultOf.Success(controlGRVStepLocalRepository.getLocalControlGRVStepByReference(
                    reference = reference,
                    type = ControlGRVStepBusiness.ControlGRVStep0::class)
                )
                1 -> ResultOf.Success(controlGRVStepLocalRepository.getLocalControlGRVStepByReference(
                    reference = reference,
                    type = ControlGRVStepBusiness.ControlGRVStep1::class)
                )
                else -> ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)
            }

        } catch (e: Exception) {
            ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)
        }
    }
}