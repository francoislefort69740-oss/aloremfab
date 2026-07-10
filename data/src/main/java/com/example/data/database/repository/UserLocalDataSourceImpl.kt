package com.example.data.database.repository

import com.example.data.database.dao.UserDao
import com.example.data.mapper.UserMapper
import com.example.domain.model.UserBusiness

open class UserLocalDataSourceImpl(private val userDao: UserDao): UserLocalDataSource {
    override suspend fun createLocalUser(userBusiness: UserBusiness) {
        userDao.insertUser(UserMapper.userBusinessToLocal(userBusiness = userBusiness))
    }

    override suspend fun getLocalUserById(userId: Int): UserBusiness {
        return UserMapper.userLocalToBusiness(userLocal = userDao.findUserDataById(userId))
    }

    override suspend fun getUserLocalByName(firstName: String, lastName: String): UserBusiness {
        return UserMapper.userLocalToBusiness(userLocal = userDao.findUserByName(first = firstName, last = lastName))
    }

    override suspend fun checkIfUserExist(userId: Int): Boolean {
        return userDao.userIdExist(userId = userId)
    }

    override suspend fun updateLocalUser(userBusiness: UserBusiness) {
        userDao.update(UserMapper.userBusinessToLocal(userBusiness = userBusiness))
    }
}