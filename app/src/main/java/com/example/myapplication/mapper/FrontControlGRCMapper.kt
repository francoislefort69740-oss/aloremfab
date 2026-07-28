package com.example.myapplication.mapper

import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ControlGRVStepBusiness
import com.example.myapplication.model.ControlGRV
import com.example.myapplication.model.StepControlGRV

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

    fun controlGRVStepBusinessToFront(controlGRVStepBusiness: ControlGRVStepBusiness): StepControlGRV = when(controlGRVStepBusiness) {
        is ControlGRVStepBusiness.ControlGRVStep0 -> StepControlGRV.Step0ControlGRV(
            reference = controlGRVStepBusiness.reference,
            reportNumber = controlGRVStepBusiness.reportNumber,
            customer = controlGRVStepBusiness.customer,
            customerSerialNumber = controlGRVStepBusiness.customerSerialNumber,
            serialNumberAlorem = controlGRVStepBusiness.serialNumberAlorem,
            type = controlGRVStepBusiness.type,
            controlGRVForeignId = controlGRVStepBusiness.controlGRVForeignId
        )
    }

    fun controlGRVStepFrontToBusiness(stepControlGRV: StepControlGRV): ControlGRVStepBusiness = when(stepControlGRV) {
        is StepControlGRV.Step0ControlGRV -> ControlGRVStepBusiness.ControlGRVStep0(
            reference = stepControlGRV.reference,
            reportNumber = stepControlGRV.reportNumber,
            customer = stepControlGRV.customer,
            customerSerialNumber = stepControlGRV.customerSerialNumber,
            serialNumberAlorem = stepControlGRV.serialNumberAlorem,
            type = stepControlGRV.type,
            controlGRVForeignId = stepControlGRV.reference,
        )
    }
}