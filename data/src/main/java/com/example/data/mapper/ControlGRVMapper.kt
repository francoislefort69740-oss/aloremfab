package com.example.data.mapper

import com.example.data.database.entities.ControlGRVLocal
import com.example.domain.model.ControlGRVBusiness

object ControlGRVMapper {

    fun controlGRVLocalToBusiness(controlGRVLocal: ControlGRVLocal): ControlGRVBusiness = ControlGRVBusiness(
        uid = controlGRVLocal.uid,
        serialNumber = controlGRVLocal.serialNumber,
        currentStep = controlGRVLocal.currentStep,
        currentlyGoingOn = controlGRVLocal.currentlyGoingOn
    )

    fun controlGRVBusinessToLocal(controlGRVBusiness: ControlGRVBusiness): ControlGRVLocal = ControlGRVLocal(
        uid = controlGRVBusiness.uid!!,
        serialNumber = controlGRVBusiness.serialNumber!!,
        currentStep = controlGRVBusiness.currentStep,
        currentlyGoingOn = true
    )

    fun allControlGRVLocalToBusiness(controlGRVLocals: List<ControlGRVLocal>): List<ControlGRVBusiness> {
        val result = mutableListOf<ControlGRVBusiness>()
        controlGRVLocals.forEach {
            result.add(controlGRVLocalToBusiness(it))
        }
        return result
    }

}