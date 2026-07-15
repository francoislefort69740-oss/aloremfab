package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.UserBusiness
import com.example.domain.repository.db.UserLocalRepository
import java.lang.Exception

class GetAllUsersUseCase(private val userLocalRepository: UserLocalRepository) {
    suspend operator fun invoke(): ResultOf<List<UserBusiness>> {
        val list = userLocalRepository.getAllUsers()
        return if (list.isNotEmpty()) ResultOf.Success(list)
        else ResultOf.Error(Exception("NO USER FOUND"))
    }

}