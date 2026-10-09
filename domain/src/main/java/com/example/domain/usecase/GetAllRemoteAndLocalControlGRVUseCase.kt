package com.example.domain.usecase

import com.example.domain.repository.db.ControlGRVLocalRepository

class GetAllRemoteAndLocalControlGRVUseCase(private val controlGRVLocalRepository: ControlGRVLocalRepository) {
    suspend operator fun invoke(baseUrl: String) = controlGRVLocalRepository.getAllRemoteAndLocalControlGRV(baseUrl)
}