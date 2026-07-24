package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVStepLocalRepository

class GetControlGRVStepUseCase(private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository) {
    suspend operator fun invoke(reference: Int): ResultOf<ControlGRVStepBusiness> {
        return try {
            ResultOf.Success(controlGRVStepLocalRepository.getLocalControlGRVStep0ByReference(reference = reference))
        } catch (e: Exception) {
            ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)
        }
    }
}