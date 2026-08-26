package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository
import com.example.domain.utils.RETURN_TO_ADD_LIST_GRV_CONTROL

class UpdateControlGRCUseCase(private val controlGRVLocalRepository: ControlGRVLocalRepository) {
    suspend operator fun invoke(controlGRVBusiness: ControlGRVBusiness?, trigger: String): ResultOf<String> {

        if (controlGRVBusiness == null) return ResultOf.Error(Exception(ErrorBusiness.ControlGRVNotFound))

        // check if user local exist : if OK -> update - if NOK -> create
        val updateId = controlGRVLocalRepository.checkIfControlGRVExist(controlGRVId = controlGRVBusiness.serialNumber!!)

        // check if user local is created or updated for callback
        return if (updateId) {
            when(trigger) {
                RETURN_TO_ADD_LIST_GRV_CONTROL -> controlGRVBusiness.loaded = false
            }
            val controlCallback = controlGRVLocalRepository.updateLocalControlGRV(controlGRVBusiness = controlGRVBusiness)
            if (controlCallback != 0) {
                ResultOf.Success(trigger)
            } else {
                ResultOf.Error(Exception(ErrorBusiness.ControlGRVNotFound))
            }
        } else {
            ResultOf.Error(Exception(ErrorBusiness.ControlGRVNotFound))
        }
    }
}