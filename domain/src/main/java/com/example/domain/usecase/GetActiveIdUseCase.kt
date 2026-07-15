package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ActiveIdBusiness
import com.example.domain.repository.db.ActiveIdLocalRepository

class GetActiveIdUseCase(private val activeIdLocalRepository: ActiveIdLocalRepository) {
    suspend operator fun invoke(id: Int): ResultOf<ActiveIdBusiness> {
        return try {
            ResultOf.Success(activeIdLocalRepository.getActiveId(uiD = id))
        } catch (e: Exception) {
            ResultOf.Error(e)
        }
    }
}