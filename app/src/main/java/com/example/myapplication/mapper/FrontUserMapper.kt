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
}