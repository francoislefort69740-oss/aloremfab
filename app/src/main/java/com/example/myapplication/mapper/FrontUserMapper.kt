package com.example.myapplication.mapper

import com.example.domain.model.UserBusiness
import com.example.myapplication.model.User

object FrontUserMapper {
    fun userBusinessToFront(userBusiness: UserBusiness): User = User(
        uid = userBusiness.uid,
        firstName = userBusiness.firstName,
        lastName = userBusiness.lastName,
        email = userBusiness.email
    )

    fun allUsersBusinessToFront(userBusiness: List<UserBusiness>): List<User> {
        val result = mutableListOf<User>()
        userBusiness.forEach {
            result.add(userBusinessToFront(it))
        }
        return result
    }
}