package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ErrorBusiness
import com.example.domain.model.UserBusiness
import com.example.domain.repository.db.UserLocalRepository

class UpdateUserUseCase(private val userLocalRepository: UserLocalRepository) {
    suspend operator fun invoke(name: String, firstName: String, email: String): ResultOf<Boolean> {
        if(name != "" && firstName != "" && email != "") {

            // Create userBusiness tuple for injection
            val tempUserBusiness = createUserBusiness(name = name, firstName = firstName, email = email)

            // check if user local exist : if OK -> update - if NOK -> create
            if (userLocalRepository.checkIfUserExist(1)) userLocalRepository.updateUser(userBusiness = tempUserBusiness)
            else userLocalRepository.createUser(userBusiness = tempUserBusiness)

            // check if user local is created or updated for callback
            return if (userLocalRepository.checkIfUserExist(1)) {
                val userCallback = userLocalRepository.getUserLocalByName(firstName = firstName, lastName = name)

                if (userCallback.uid != 0) {
                    ResultOf.Success(true)
                } else {
                    ResultOf.Error(Exception(ErrorBusiness.UserRegistrationFailed))
                }

            } else {
                ResultOf.Error(Exception(ErrorBusiness.UserRegistrationFailed))
            }
        }
        return ResultOf.Error(ErrorBusiness.UserRegistrationFieldEmpty)
    }

    private fun createUserBusiness(name: String, firstName: String, email: String): UserBusiness =
        UserBusiness(uid = 1, firstName = firstName, lastName = name, email = email)
}