package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository

class UpdateLoadedControlGRVStateUseCase(private val controlGRVLocalRepository: ControlGRVLocalRepository) {
    suspend operator fun invoke(serialNumber: Int, state: Boolean): ResultOf<Pair<List<ControlGRVBusiness>, ControlGRVBusiness>> {

        // check if user local exist : if OK -> update - if NOK -> create
        val updateId = controlGRVLocalRepository.checkIfControlGRVExist(controlGRVId = serialNumber)

        // check if user local is created or updated for callback
        return if (updateId) {
            val control = controlGRVLocalRepository.getLocalControlGRVBySerialNumber(serialNumber = serialNumber)
            control.loaded = state
            val controlCallback = controlGRVLocalRepository.updateLocalControlGRV(controlGRVBusiness = control)
            if (controlCallback != 0) {
                val controls = controlGRVLocalRepository.getAllControlGRV().toMutableList()
                controls.find { it.serialNumber == serialNumber }.apply { this?.loaded = state }
                controls.removeIf { it.loaded }
                ResultOf.Success(Pair(controls, control))
            } else {
                ResultOf.Error(Exception(ErrorBusiness.ControlGRVNotFound))
            }
        } else {
            ResultOf.Error(Exception(ErrorBusiness.ControlGRVNotFound))
        }
    }
}