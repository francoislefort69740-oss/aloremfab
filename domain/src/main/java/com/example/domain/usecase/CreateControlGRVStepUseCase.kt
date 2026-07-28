package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVStepLocalRepository

class CreateControlGRVStepUseCase(private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository) {
    suspend operator fun invoke(controlGRVStepBusiness: ControlGRVStepBusiness, stepNumber: Int): ResultOf<Boolean> {
        return try {
            when (stepNumber) {
                1 -> ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)
                else -> ResultOf.Success(controlGRVStepLocalRepository.createLocalControlGRVStep0(controlGRVStepBusiness = controlGRVStepBusiness))
            }
        } catch (e: Exception) {
            ResultOf.Error(e)
        }
    }
}