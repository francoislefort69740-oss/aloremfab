package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ErrorBusiness
import com.example.domain.model.UserBusiness
import com.example.domain.repository.db.ActiveIdLocalRepository
import com.example.domain.repository.db.UserLocalRepository
import java.lang.Exception

class GetAllUsersUseCase(private val userLocalRepository: UserLocalRepository, private val activeIdLocalRepository: ActiveIdLocalRepository) {
    suspend operator fun invoke(): ResultOf<List<UserBusiness>> {
        val list = userLocalRepository.getAllUsers()
        val activeId = activeIdLocalRepository.getActiveId(0)

        list.forEach { it.isActive = it.uid == activeId.activeId }

        return if (list.isNotEmpty()) ResultOf.Success(list)
        else ResultOf.Error(ErrorBusiness.NoUserExist)
    }

}