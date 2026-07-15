package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.UserBusiness
import com.example.domain.repository.db.ActiveIdLocalRepository
import com.example.domain.repository.db.UserLocalRepository
import java.lang.Exception

class GetAllUsersUseCase(private val userLocalRepository: UserLocalRepository, private val activeIdLocalRepository: ActiveIdLocalRepository) {
    suspend operator fun invoke(): ResultOf<Pair<List<UserBusiness>, Int>> {
        val list = userLocalRepository.getAllUsers()
        val activeId = activeIdLocalRepository.getActiveId(0)
        return if (list.isNotEmpty()) ResultOf.Success(Pair(list, activeId.activeId))
        else ResultOf.Error(Exception("NO USER FOUND"))
    }

}