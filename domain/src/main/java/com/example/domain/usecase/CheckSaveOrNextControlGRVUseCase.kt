package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository
import com.example.domain.repository.db.ControlGRVStepLocalRepository

class CheckSaveOrNextControlGRVUseCase(private val controlGRVStepUseCase: ControlGRVStepLocalRepository, private val controlGRVLocalRepository: ControlGRVLocalRepository) {
    suspend operator fun invoke(reference: Int): ResultOf<Pair<Boolean, Boolean>> {
        return ResultOf.Success(Pair(
            controlGRVLocalRepository.checkIfControlGRVExist(reference),
            controlGRVStepUseCase.checkIfControlGRVStepExist(reference, ControlGRVStepBusiness.ControlGRVStep0::class))
        )
    }
}