package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ErrorBusiness
import com.example.domain.model.UserBusiness
import com.example.domain.repository.db.UserLocalRepository

class UpdateUserUseCase(private val userLocalRepository: UserLocalRepository) {
    suspend operator fun invoke(name: String, firstName: String, email: String, uid: Int): ResultOf<Boolean> {
        if(name != "" && firstName != "" && email != "") {

            // Create userBusiness tuple for injection
            val tempUserBusiness = createUserBusiness(name = name, firstName = firstName, email = email, uid = uid)

            // check if user local exist : if OK -> update - if NOK -> create
            val updateId = userLocalRepository.checkIfUserExist(uid = uid)

            // check if user local is created or updated for callback
            return if (updateId) {
                val userCallback = userLocalRepository.updateUser(createUserBusiness( firstName = firstName, name = name, email = email, uid = uid))

                if (userCallback != 0) {
                    ResultOf.Success(true)
                } else {
                    ResultOf.Error(Exception(ErrorBusiness.UserRegistrationFailed))
                }

            } else {
                ResultOf.Error(Exception(ErrorBusiness.UserRegistrationFailed))
            }
        } else {
            return if (name == "") ResultOf.Error(ErrorBusiness.UserRegistrationNameFieldEmpty)
            else if (firstName == "") ResultOf.Error(ErrorBusiness.UserRegistrationForNameFieldEmpty)
            else ResultOf.Error(ErrorBusiness.UserRegistrationEmailFieldEmpty)
        }
    }

    private fun createUserBusiness(name: String, firstName: String, email: String, uid: Int): UserBusiness =
        UserBusiness(uid = uid, firstName = firstName, lastName = name, email = email)
}