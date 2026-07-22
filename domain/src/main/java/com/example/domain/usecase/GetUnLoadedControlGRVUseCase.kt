package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository

class GetUnLoadedControlGRVUseCase(private val controlGRVLocalRepository: ControlGRVLocalRepository) {
    suspend operator fun invoke(): ResultOf<List<ControlGRVBusiness>> {
        val list = controlGRVLocalRepository.getUnloaded()

        return if (list.isNotEmpty()) ResultOf.Success(list)
        else ResultOf.Error(ErrorBusiness.NoControlGRVExist)
    }
}