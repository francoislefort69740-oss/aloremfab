package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVStepLocalRepository

class GetControlGRVStepUseCase(private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository) {
    suspend operator fun invoke(reference: Int, stepNumber: Int): ResultOf<ControlGRVStepBusiness> {
        return try {
            when (stepNumber) {
                1 -> ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)
                else -> ResultOf.Success(controlGRVStepLocalRepository.getLocalControlGRVStep0ByReference(reference = reference))
            }

        } catch (e: Exception) {
            ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)
        }
    }
}