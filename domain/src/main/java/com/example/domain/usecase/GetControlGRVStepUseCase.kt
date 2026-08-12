package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVStepLocalRepository
import com.example.domain.utils.GRVControlStepEnum
import com.example.domain.utils.getKClass

class GetControlGRVStepUseCase(private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository) {
    suspend operator fun invoke(reference: Int, stepNumber: GRVControlStepEnum): ResultOf<ControlGRVStepBusiness> {
        return try {
            ResultOf.Success(controlGRVStepLocalRepository.getLocalControlGRVStepByReference(reference =  reference, type = getKClass(stepNumber = stepNumber)))
        } catch (e: Exception) {
            ResultOf.Error(ErrorBusiness.ControlGRVStepNotInitialized)
        }
    }
}