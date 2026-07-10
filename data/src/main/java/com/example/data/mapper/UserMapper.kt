package com.example.data.mapper

import com.example.data.database.entities.UserLocal
import com.example.domain.model.UserBusiness

object UserMapper {

    fun userLocalToBusiness(userLocal: UserLocal): UserBusiness = UserBusiness(
        uid = userLocal.uid,
        firstName = userLocal.firstName,
        lastName = userLocal.lastName,
        email = userLocal.email
    )

    fun userBusinessToLocal(userBusiness: UserBusiness?): UserLocal = UserLocal(
        uid = userBusiness?.uid ?: 0,
        firstName = userBusiness?.firstName ?: "",
        lastName = userBusiness?.lastName ?: "",
        email = userBusiness?.email ?: ""
    )

}