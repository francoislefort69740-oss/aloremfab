package com.example.domain.repository.db

import com.example.domain.model.UserBusiness

interface UserLocalRepository {
    //CREATE
    suspend fun createUser(userBusiness: UserBusiness): Int

    //READ
    suspend fun getUserLocal(userInt: Int): UserBusiness
    suspend fun getUserLocalByName(firstName: String, lastName: String): UserBusiness
    suspend fun checkIfUserExist(uid: Int): Boolean
    suspend fun getAllUsers(): List<UserBusiness>

    // UPDATE
    suspend fun updateUser(userBusiness: UserBusiness): Int

    //DELETE
    suspend fun deleteUser(uid: Int): Boolean
}