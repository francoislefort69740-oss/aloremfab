package com.example.data.database.repository

import com.example.domain.model.UserBusiness

interface UserLocalDataSource {
    //CREATE
    suspend fun createLocalUser(userBusiness: UserBusiness): Int

    //READ
    suspend fun getLocalUserById(userId: Int): UserBusiness
    suspend fun getUserLocalByName(firstName: String, lastName: String): UserBusiness
    suspend fun checkIfUserExist(userId: Int): Boolean
    suspend fun getAllUsers(): List<UserBusiness>

    //UPDATE
    suspend fun updateLocalUser(userBusiness: UserBusiness): Int

    //DELETE

}