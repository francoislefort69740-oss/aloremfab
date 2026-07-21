package com.example.domain.usecase

import android.util.Log
import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository

class CreateControlGRVUseCase(private val controlGRVLocalRepository: ControlGRVLocalRepository) {
    suspend operator fun invoke(controlGRVBusiness: ControlGRVBusiness): ResultOf<Boolean> {
        return try {
            if (controlGRVBusiness.serialNumber != null){
                val allControls = controlGRVLocalRepository.getAllControlGRV()
                var goodId = false
                var mResult = 0

                while (!goodId){
                    try {
                        val result = controlGRVLocalRepository.createLocalControlGRV(
                            ControlGRVBusiness(
                                uid = allControls.size + mResult,
                                serialNumber = controlGRVBusiness.serialNumber,
                                currentStep = 1,
                                currentlyGoingOn = true
                            )
                        )
                        goodId = true
                        mResult = result
                    } catch (e: Exception){
                        Log.i("exeption", e.toString())
                        mResult += 1
                    }
                }

                if (controlGRVLocalRepository.checkIfControlGRVExist(controlGRVId = controlGRVBusiness.uid!!)) {
                    ResultOf.Success(true)
                } else {
                    ResultOf.Error(ErrorBusiness.ControlGRVNotFound)
                }
            } else {
                ResultOf.Error(ErrorBusiness.UserRegistrationFieldEmpty)
            }

        } catch (e: Exception) {
            ResultOf.Error(ErrorBusiness.ControlGRVNotFound)
        }


    }
}