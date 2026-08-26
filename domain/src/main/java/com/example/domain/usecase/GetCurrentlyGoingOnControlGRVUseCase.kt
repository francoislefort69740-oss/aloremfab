package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository

class GetCurrentlyGoingOnControlGRVUseCase (private val controlGRVLocalRepository: ControlGRVLocalRepository) {
    suspend operator fun invoke(): ResultOf<List<ControlGRVBusiness>> {
        val list = controlGRVLocalRepository.getCurrentlyGoingOn()

        return if (list.isNotEmpty()){
            list.forEach { control ->
                if (control.loaded) {
                    control.loaded = false
                    controlGRVLocalRepository.updateLocalControlGRV(controlGRVBusiness = control)
                }
            }
            ResultOf.Success(list)
        }
        else ResultOf.Error(ErrorBusiness.NoControlGRVExist)
    }

}