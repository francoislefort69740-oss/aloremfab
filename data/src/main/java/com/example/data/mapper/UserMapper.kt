package com.example.data.mapper

import com.example.data.database.entities.ActiveIdLocal
import com.example.data.database.entities.UserLocal
import com.example.domain.model.ActiveIdBusiness
import com.example.domain.model.UserBusiness
import kotlin.collections.forEach

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

    fun allUsersLocalToBusiness(userLocals: List<UserLocal>): List<UserBusiness> {
        val result = mutableListOf<UserBusiness>()
        userLocals.forEach {
            result.add(userLocalToBusiness(it))
        }
        return result
    }

    // ACTIVE ID

    fun activeIdBusinessToLocal(activeIdBusiness: ActiveIdBusiness): ActiveIdLocal = ActiveIdLocal(
        uid = activeIdBusiness.uid,
        activeId = activeIdBusiness.activeId
    )

    fun activeIdLocalToBusiness(activeIdLocal: ActiveIdLocal): ActiveIdBusiness = ActiveIdBusiness(
        uid = activeIdLocal.uid,
        activeId = activeIdLocal.activeId
    )



}