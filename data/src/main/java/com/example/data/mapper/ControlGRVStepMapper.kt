package com.example.data.mapper

import com.example.data.database.entities.ControlGRVStep0Local
import com.example.domain.model.ControlGRVStepBusiness

object ControlGRVStepMapper {

    fun controlGRVStep0LocalToBusiness(controlGRVStep0Local: ControlGRVStep0Local): ControlGRVStepBusiness =
        ControlGRVStepBusiness.ControlGRVStep0(
            reference = controlGRVStep0Local.reference,
            reportNumber = controlGRVStep0Local.reportNumber,
            customer = controlGRVStep0Local.customer,
            customerSerialNumber = controlGRVStep0Local.customerSerialNumber,
            serialNumberAlorem = controlGRVStep0Local.serialNumberAlorem,
            type = controlGRVStep0Local.type,
            controlGRVForeignId = controlGRVStep0Local.foreignKey
        )

    fun controlGRVStepBusinessToLocal0(controlGRVStepBusiness: ControlGRVStepBusiness): ControlGRVStep0Local = when (controlGRVStepBusiness) {
        is ControlGRVStepBusiness.ControlGRVStep0 -> ControlGRVStep0Local(
            reference = controlGRVStepBusiness.reference,
            reportNumber = controlGRVStepBusiness.reportNumber,
            customer = controlGRVStepBusiness.customer,
            customerSerialNumber = controlGRVStepBusiness.customerSerialNumber,
            serialNumberAlorem = controlGRVStepBusiness.serialNumberAlorem,
            type = controlGRVStepBusiness.type,
            foreignKey = controlGRVStepBusiness.controlGRVForeignId
        )

    }



}