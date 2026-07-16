package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ActiveIdBusiness
import com.example.domain.repository.db.ActiveIdLocalRepository

class UpdateActivateIdUseCase(private val activeIdLocalRepository: ActiveIdLocalRepository) {
    suspend operator fun invoke(activeId: Int): ResultOf<Int> {
        return try {
            ResultOf.Success(activeIdLocalRepository.updateActiveId(activeIdBusiness = ActiveIdBusiness(activeId = activeId, uid = 0)))
        } catch (e: Exception) {
            ResultOf.Error(e)
        }
    }
}