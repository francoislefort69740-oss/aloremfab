package com.example.data.mapper

import com.example.data.database.entities.ControlGRVStep0Local
import com.example.data.database.entities.ControlGRVStep1Local
import com.example.data.database.entities.ControlGRVStep2Local
import com.example.data.database.entities.ControlGRVStep3Local
import com.example.data.database.entities.ControlGRVStep4Local
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

    fun controlGRVStep2LocalToBusiness(controlGRVStep2Local: ControlGRVStep2Local): ControlGRVStepBusiness =
        ControlGRVStepBusiness.ControlGRVStep2(
            reference = controlGRVStep2Local.reference,
            tare = controlGRVStep2Local.tare,
            material = controlGRVStep2Local.material,
            capacity20 = controlGRVStep2Local.capacity20,
            grossMass = controlGRVStep2Local.grossMass,
            fabricationDate = controlGRVStep2Local.fabricationDate,
            shellThickness = controlGRVStep2Local.shellThickness,
            pictogramStacking = controlGRVStep2Local.pictogramStacking,
            weightStacking = controlGRVStep2Local.weightStacking,
            controlGRVForeignId = controlGRVStep2Local.foreignKey
        )

    fun controlGRVStep3LocalToBusiness(controlGRVStep3Local: ControlGRVStep3Local): ControlGRVStepBusiness =
        ControlGRVStepBusiness.ControlGRVStep3(
            reference = controlGRVStep3Local.reference,
            bottomRetentionFace = controlGRVStep3Local.bottomRetentionFace,
            bottomRetentionRight = controlGRVStep3Local.bottomRetentionRight,
            bottomRetentionLeft = controlGRVStep3Local.bottomRetentionLeft,
            bottomRetentionBehind = controlGRVStep3Local.bottomRetentionBehind,
            upperRetention = controlGRVStep3Local.upperRetention,
            liftingRings = controlGRVStep3Local.liftingRings,
            forkliftPass = controlGRVStep3Local.forkliftPass,
            dashboard = controlGRVStep3Local.dashboard,
            unauthorizedRepair = controlGRVStep3Local.unauthorizedRepair,
            controlGRVForeignId = controlGRVStep3Local.foreignKey
        )

    fun controlGRVStep4LocalToBusiness(controlGRVStep4Local: ControlGRVStep4Local): ControlGRVStepBusiness =
        ControlGRVStepBusiness.ControlGRVStep4(
            reference = controlGRVStep4Local.reference,
            internalNA = controlGRVStep4Local.internalNA,
            internalOK = controlGRVStep4Local.internalOK,
            internalClean = controlGRVStep4Local.internalClean,
            internalObjectInside = controlGRVStep4Local.internalObjectInside,
            internalPollution = controlGRVStep4Local.internalPollution,
            controlGRVForeignId = controlGRVStep4Local.foreignKey
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

    fun controlGRVStepBusinessToLocal2(controlGRVStepBusiness: ControlGRVStepBusiness.ControlGRVStep2): ControlGRVStep2Local =
        ControlGRVStep2Local(
            reference = controlGRVStepBusiness.reference,
            tare = controlGRVStepBusiness.tare,
            material = controlGRVStepBusiness.material,
            capacity20 = controlGRVStepBusiness.capacity20,
            grossMass = controlGRVStepBusiness.grossMass,
            fabricationDate = controlGRVStepBusiness.fabricationDate,
            shellThickness = controlGRVStepBusiness.shellThickness,
            pictogramStacking = controlGRVStepBusiness.pictogramStacking,
            weightStacking = controlGRVStepBusiness.weightStacking,
            foreignKey = controlGRVStepBusiness.controlGRVForeignId
        )

    fun controlGRVStepBusinessToLocal3(controlGRVStepBusiness: ControlGRVStepBusiness.ControlGRVStep3): ControlGRVStep3Local =
        ControlGRVStep3Local(
            reference = controlGRVStepBusiness.reference,
            bottomRetentionFace = controlGRVStepBusiness.bottomRetentionFace,
            bottomRetentionRight = controlGRVStepBusiness.bottomRetentionRight,
            bottomRetentionLeft = controlGRVStepBusiness.bottomRetentionLeft,
            bottomRetentionBehind = controlGRVStepBusiness.bottomRetentionBehind,
            upperRetention = controlGRVStepBusiness.upperRetention,
            liftingRings = controlGRVStepBusiness.liftingRings,
            forkliftPass = controlGRVStepBusiness.forkliftPass,
            dashboard = controlGRVStepBusiness.dashboard,
            unauthorizedRepair = controlGRVStepBusiness.unauthorizedRepair,
            foreignKey = controlGRVStepBusiness.controlGRVForeignId
        )

    fun controlGRVStepBusinessToLocal4(controlGRVStepBusiness: ControlGRVStepBusiness.ControlGRVStep4): ControlGRVStep4Local =
        ControlGRVStep4Local(
            reference = controlGRVStepBusiness.reference,
            internalNA = controlGRVStepBusiness.internalNA,
            internalOK = controlGRVStepBusiness.internalOK,
            internalClean = controlGRVStepBusiness.internalClean,
            internalObjectInside = controlGRVStepBusiness.internalObjectInside,
            internalPollution = controlGRVStepBusiness.internalPollution,
            foreignKey = controlGRVStepBusiness.controlGRVForeignId
        )
}