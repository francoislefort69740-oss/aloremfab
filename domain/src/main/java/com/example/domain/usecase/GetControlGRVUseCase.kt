package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository

class GetControlGRVUseCase(private val controlGRVLocalRepository: ControlGRVLocalRepository) {
    suspend operator fun invoke(id: Int): ResultOf<ControlGRVBusiness> {
        return try {
            val controlGRV = controlGRVLocalRepository.getLocalControlGRVById(id)
            ResultOf.Success(controlGRV)
        } catch (e: Exception) {
            ResultOf.Error(ErrorBusiness.ControlGRVNotFound)
        }
    }
}
