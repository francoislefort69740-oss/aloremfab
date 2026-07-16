package com.example.data.repository

import com.example.data.database.repository.UserLocalDataSource
import com.example.domain.model.UserBusiness
import com.example.domain.repository.db.UserLocalRepository

class UserRepositoryImpl(private val userLocalDataSource: UserLocalDataSource): UserLocalRepository {
    override suspend fun createUser(userBusiness: UserBusiness): Int {
        return userLocalDataSource.createLocalUser(userBusiness = userBusiness)
    }

    override suspend fun getUserLocal(userInt: Int): UserBusiness {
        return userLocalDataSource.getLocalUserById(userId = userInt)
    }

    override suspend fun getUserLocalByName(firstName: String, lastName: String): UserBusiness {
        return userLocalDataSource.getUserLocalByName(firstName = firstName, lastName = lastName)
    }

    override suspend fun checkIfUserExist(uid: Int): Boolean {
        return userLocalDataSource.checkIfUserExist(userId = uid)
    }

    override suspend fun getAllUsers(): List<UserBusiness> {
        return userLocalDataSource.getAllUsers()
    }

    override suspend fun updateUser(userBusiness: UserBusiness): Int {
        return userLocalDataSource.updateLocalUser(userBusiness = userBusiness)
    }

    override suspend fun deleteUser(uid: Int): Boolean {
        return userLocalDataSource.deleteUser(uid = uid)
    }
}