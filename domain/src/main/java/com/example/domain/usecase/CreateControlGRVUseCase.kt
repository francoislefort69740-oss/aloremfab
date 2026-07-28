package com.example.domain.usecase

import android.util.Log
import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository
import com.example.domain.repository.db.ControlGRVStepLocalRepository

class CreateControlGRVUseCase(private val controlGRVLocalRepository: ControlGRVLocalRepository, private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository) {
    suspend operator fun invoke(controlGRVBusiness: ControlGRVBusiness): ResultOf<Pair<List<ControlGRVBusiness>, ControlGRVBusiness>> {
        return try {
            if (controlGRVBusiness.serialNumber != null){
                var goodId = false
                var mResult = 0

                while (!goodId){
                    try {
                        val result = controlGRVLocalRepository.createLocalControlGRV(
                            ControlGRVBusiness(
                                uid = controlGRVBusiness.serialNumber,
                                serialNumber = controlGRVBusiness.serialNumber,
                                currentStep = 1,
                                currentlyGoingOn = true,
                                loaded = false
                            )
                        )
                        goodId = true
                        mResult = result
                    } catch (e: Exception){
                        Log.i("exeption", e.toString())
                        mResult += 1
                    }
                }

                if (controlGRVLocalRepository.checkIfControlGRVExist(controlGRVId = controlGRVBusiness.serialNumber)) {
                    controlGRVStepLocalRepository.createLocalControlGRVStep0(
                        ControlGRVStepBusiness.ControlGRVStep0(
                            reference = controlGRVBusiness.serialNumber,
                            reportNumber = 0,
                            customer = "",
                            customerSerialNumber = 0,
                            serialNumberAlorem = 0,
                            type = "",
                            controlGRVForeignId = controlGRVBusiness.serialNumber
                        )
                    )
                    ResultOf.Success(Pair(controlGRVLocalRepository.getUnloaded(),controlGRVBusiness))
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