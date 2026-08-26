package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository

class GetLoadedControlGRVUseCase(private val controlGRVLocalRepository: ControlGRVLocalRepository) {
    suspend operator fun invoke(): ResultOf<List<ControlGRVBusiness>> {
        val list = controlGRVLocalRepository.getLoaded()

        return if (list.isNotEmpty()) ResultOf.Success(list)
        else ResultOf.Error(ErrorBusiness.NoControlGRVExist)
    }
}