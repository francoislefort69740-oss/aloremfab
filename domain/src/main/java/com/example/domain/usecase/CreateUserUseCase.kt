package com.example.domain.usecase

import android.util.Log
import com.example.domain.ResultOf
import com.example.domain.model.ErrorBusiness
import com.example.domain.model.UserBusiness
import com.example.domain.repository.db.UserLocalRepository

class CreateUserUseCase(private val userLocalRepository: UserLocalRepository) {
    suspend operator fun invoke(name: String, firstName: String, email: String): ResultOf<Boolean>{
        return try {
            if (name != "" && firstName != "" && email != "") {
                val allUsers = userLocalRepository.getAllUsers()

                var goodId = false
                var mResult = 1

                while (!goodId){
                    try {
                        val result = userLocalRepository.createUser(
                            UserBusiness(
                                uid = allUsers.size + mResult,
                                firstName = firstName,
                                lastName = name,
                                email = email
                            )
                        )
                        goodId = true
                        mResult = result
                    } catch (e: Exception){
                        Log.i("exeption", e.toString())
                        mResult += 1
                    }
                }

                if (userLocalRepository.checkIfUserExist(uid = mResult)) {
                    ResultOf.Success(true)
                } else {
                    ResultOf.Error(ErrorBusiness.UserNotFound)
                }
            } else {
                if (name == "") ResultOf.Error(ErrorBusiness.UserRegistrationNameFieldEmpty)
                else if (firstName == "") ResultOf.Error(ErrorBusiness.UserRegistrationForNameFieldEmpty)
                else ResultOf.Error(ErrorBusiness.UserRegistrationEmailFieldEmpty)
            }
        } catch (e: Exception) {
            ResultOf.Error(ErrorBusiness.UserNotFound)
        }
    }
}