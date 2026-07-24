package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.repository.db.ControlGRVStepLocalRepository

class CreateControlGRVStepUseCase(private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository) {
    suspend operator fun invoke(controlGRVStepBusiness: ControlGRVStepBusiness): ResultOf<Boolean> {
        return try {
            ResultOf.Success(controlGRVStepLocalRepository.createLocalControlGRVStep0(controlGRVStepBusiness = controlGRVStepBusiness))
        } catch (e: Exception) {
            ResultOf.Error(e)
        }
    }
}