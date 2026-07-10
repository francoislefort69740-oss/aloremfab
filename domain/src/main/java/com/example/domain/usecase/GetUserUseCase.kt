package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ErrorBusiness
import com.example.domain.model.UserBusiness
import com.example.domain.repository.db.UserLocalRepository

class GetUserUseCase (private val userLocalRepository: UserLocalRepository) {
    suspend operator fun invoke(id: Int): ResultOf<UserBusiness>{
        return try {
            ResultOf.Success(userLocalRepository.getUserLocal(userInt = id))
        } catch (e: Exception) {
            ResultOf.Error(ErrorBusiness.UserNotFound)
        }
    }
}