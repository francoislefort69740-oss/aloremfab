package com.example.myapplication.mapper

import com.example.domain.model.ControlGRVBusiness
import com.example.myapplication.model.ControlGRV

object FrontControlGRCMapper {
    fun allControlGRVBusinessToFront(controlGRVBusiness: List<ControlGRVBusiness>): List<ControlGRV> {
        val result = mutableListOf<ControlGRV>()
        controlGRVBusiness.forEach {
            result.add(controlGRVBusinessToFront(it))
        }
        return result
    }

    fun controlGRVBusinessToFront(controlGRVBusiness: ControlGRVBusiness): ControlGRV = ControlGRV(
        uid = controlGRVBusiness.uid,
        serialNumber = controlGRVBusiness.serialNumber
    )

    fun controlGRVFrontToBusiness(controlGRV: ControlGRV): ControlGRVBusiness = ControlGRVBusiness(
        uid = controlGRV.uid,
        serialNumber = controlGRV.serialNumber,
        currentStep = controlGRV.currentStep?.id ?: 1,
        currentlyGoingOn = true
    )
}