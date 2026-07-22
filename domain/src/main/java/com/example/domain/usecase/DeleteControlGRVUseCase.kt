package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository

class DeleteControlGRVUseCase(private val controlGRVLocalRepository: ControlGRVLocalRepository) {
    suspend operator fun invoke(id: Int): ResultOf<List<ControlGRVBusiness>> {
        return try {
            if (controlGRVLocalRepository.deleteControlGRV(id)) {
                val list = controlGRVLocalRepository.getAllControlGRV()
                return ResultOf.Success(list)
            } else {
                ResultOf.Error(exception = ErrorBusiness.ControlGRVNotFound)
            }
        } catch (e: ErrorBusiness.ControlGRVNotFound) {
            ResultOf.Error(exception = e)
        }
    }

}