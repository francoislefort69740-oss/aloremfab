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
        serialNumber = controlGRVBusiness.serialNumber,
        loaded = controlGRVBusiness.loaded
    )

    fun controlGRVFrontToBusiness(controlGRV: ControlGRV): ControlGRVBusiness {
        val business = ControlGRVBusiness(
            uid = controlGRV.uid,
            serialNumber = controlGRV.serialNumber,
            currentStep = controlGRV.currentStep,
            currentlyGoingOn = true,
            loaded = controlGRV.loaded
        )
        return business
    }
}