package com.example.data.mapper

import com.example.data.database.entities.ControlGRVStep0Local
import com.example.data.database.entities.ControlGRVStep1Local
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

    fun controlGRVStep1LocalToBusiness(controlGRVStep1Local: ControlGRVStep1Local): ControlGRVStepBusiness =
        ControlGRVStepBusiness.ControlGRVStep1(
            reference = controlGRVStep1Local.reference,
            fabricationPlateAdr = controlGRVStep1Local.fabricationPlateAdr,
            aloremPlate = controlGRVStep1Local.aloremPlate,
            bookletPouch = controlGRVStep1Local.bookletPouch,
            userManual = controlGRVStep1Local.userManual,
            instructionOfUse = controlGRVStep1Local.instructionOfUse,
            certificatesADR = controlGRVStep1Local.certificatesADR,
            groundingAdhesive = controlGRVStep1Local.groundingAdhesive,
            conformityCertificateMarking = controlGRVStep1Local.conformityCertificateMarking,
            controlGRVForeignId = controlGRVStep1Local.foreignKey
        )

    fun controlGRVStepBusinessToLocal0(controlGRVStepBusiness: ControlGRVStepBusiness.ControlGRVStep0): ControlGRVStep0Local =
        ControlGRVStep0Local(
            reference = controlGRVStepBusiness.reference,
            reportNumber = controlGRVStepBusiness.reportNumber,
            customer = controlGRVStepBusiness.customer,
            customerSerialNumber = controlGRVStepBusiness.customerSerialNumber,
            serialNumberAlorem = controlGRVStepBusiness.serialNumberAlorem,
            type = controlGRVStepBusiness.type,
            foreignKey = controlGRVStepBusiness.controlGRVForeignId
        )

    fun controlGRVStepBusinessToLocal1(controlGRVStepBusiness: ControlGRVStepBusiness.ControlGRVStep1): ControlGRVStep1Local =
        ControlGRVStep1Local(
            reference = controlGRVStepBusiness.reference,
            fabricationPlateAdr = controlGRVStepBusiness.fabricationPlateAdr,
            aloremPlate = controlGRVStepBusiness.aloremPlate,
            bookletPouch = controlGRVStepBusiness.bookletPouch,
            userManual = controlGRVStepBusiness.userManual,
            instructionOfUse = controlGRVStepBusiness.instructionOfUse,
            certificatesADR = controlGRVStepBusiness.certificatesADR,
            groundingAdhesive = controlGRVStepBusiness.groundingAdhesive,
            conformityCertificateMarking = controlGRVStepBusiness.conformityCertificateMarking,
            foreignKey = controlGRVStepBusiness.controlGRVForeignId
        )



}