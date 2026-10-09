package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository

class GetAllRemoteAndLocalControlGRVUseCase(private val controlGRVLocalRepository: ControlGRVLocalRepository) {
    suspend operator fun invoke(baseUrl: String) : ResultOf<Pair<List<ControlGRVBusiness>, List<ControlGRVBusiness>>> {
        return ResultOf.Success(controlGRVLocalRepository.getAllRemoteAndLocalControlGRV(baseUrl))
    }
}