package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository
import com.example.domain.repository.db.ControlGRVStepLocalRepository

class GetAllFinishedControlGRVUseCase(private val controlGRVLocalRepository: ControlGRVLocalRepository, private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository) {
    suspend operator fun invoke(): ResultOf<List<ControlGRVBusiness>> {
        val list = controlGRVLocalRepository.getAllControlGRV().toMutableList()
        list.forEach {
            val step0 = controlGRVStepLocalRepository.getLocalControlGRVStepByReference(reference = it.serialNumber!!, type = ControlGRVStepBusiness.ControlGRVStep0::class)
            it.title = (step0 as ControlGRVStepBusiness.ControlGRVStep0).type + " - Alorem : " + step0.serialNumberAlorem + " - SN : " + it.serialNumber.toString()
        }

        return if (list.isNotEmpty()) ResultOf.Success(list.filter { !it.currentlyGoingOn })
        else ResultOf.Success(emptyList())
    }
}