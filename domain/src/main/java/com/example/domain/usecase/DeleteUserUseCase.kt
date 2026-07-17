package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.UserBusiness
import com.example.domain.repository.db.ActiveIdLocalRepository
import com.example.domain.repository.db.UserLocalRepository
import java.lang.Exception

class DeleteUserUseCase(private val userLocalRepository: UserLocalRepository, private val activeIdLocalRepository: ActiveIdLocalRepository) {
    suspend operator fun invoke(uid: Int): ResultOf<List<UserBusiness>> {
        return try {
            if (userLocalRepository.deleteUser(uid = uid)){
                val list = userLocalRepository.getAllUsers()
                val activeId = activeIdLocalRepository.getActiveId(0)

                list.forEach { it.isActive = it.uid == activeId.activeId }

                return if (list.isNotEmpty()) ResultOf.Success(list)
                else ResultOf.Error(Exception("NO USER FOUND"))
            } else {
                ResultOf.Error(Exception("USER NOT FOUND"))
            }
        } catch (e: Exception) {
            ResultOf.Error(exception = e)
        }
    }
}