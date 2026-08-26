package com.example.data.mapper

import com.example.data.database.entities.ControlGRVStep0Local
import com.example.data.database.entities.ControlGRVStep1Local
import com.example.data.database.entities.ControlGRVStep2Local
import com.example.data.database.entities.ControlGRVStep3Local
import com.example.data.database.entities.ControlGRVStep4Local
import com.example.data.database.entities.ControlGRVStep5Local
import com.example.domain.model.ControlGRVStepBusiness

object ControlGRVStepMapper {

    /// ---> LOCAL TO BUSINESS

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

    fun controlGRVStep5LocalToBusiness(controlGRVStep5Local: ControlGRVStep5Local): ControlGRVStepBusiness =
        ControlGRVStepBusiness.ControlGRVStep5(
            reference = controlGRVStep5Local.reference,
            epaisseurNA = controlGRVStep5Local.epaisseurNA,
            epaisseurMinSideFront = controlGRVStep5Local.epaisseurMinSideFront,
            epaisseurMinSideBack = controlGRVStep5Local.epaisseurMinSideBack,
            epaisseurMinSideRight = controlGRVStep5Local.epaisseurMinSideRight,
            epaisseurMinSideLeft = controlGRVStep5Local.epaisseurMinSideLeft,
            epaisseurSideFrontResult1 = controlGRVStep5Local.epaisseurSideFrontResult1,
            epaisseurSideFrontResult2 = controlGRVStep5Local.epaisseurSideFrontResult2,
            epaisseurSideFrontResult3 = controlGRVStep5Local.epaisseurSideFrontResult3,
            epaisseurSideFrontResult4 = controlGRVStep5Local.epaisseurSideFrontResult4,
            epaisseurSideFrontResult5 = controlGRVStep5Local.epaisseurSideFrontResult5,
            epaisseurSideBackResult1 = controlGRVStep5Local.epaisseurSideBackResult1,
            epaisseurSideBackResult2 = controlGRVStep5Local.epaisseurSideBackResult2,
            epaisseurSideBackResult3 = controlGRVStep5Local.epaisseurSideBackResult3,
            epaisseurSideBackResult4 = controlGRVStep5Local.epaisseurSideBackResult4,
            epaisseurSideBackResult5 = controlGRVStep5Local.epaisseurSideBackResult5,
            epaisseurSideRightResult1 = controlGRVStep5Local.epaisseurSideRightResult1,
            epaisseurSideRightResult2 = controlGRVStep5Local.epaisseurSideRightResult2,
            epaisseurSideRightResult3 = controlGRVStep5Local.epaisseurSideRightResult3,
            epaisseurSideRightResult4 = controlGRVStep5Local.epaisseurSideRightResult4,
            epaisseurSideRightResult5 = controlGRVStep5Local.epaisseurSideRightResult5,
            epaisseurSideLeftResult1 = controlGRVStep5Local.epaisseurSideLeftResult1,
            epaisseurSideLeftResult2 = controlGRVStep5Local.epaisseurSideLeftResult2,
            epaisseurSideLeftResult3 = controlGRVStep5Local.epaisseurSideLeftResult3,
            epaisseurSideLeftResult4 = controlGRVStep5Local.epaisseurSideLeftResult4,
            epaisseurSideLeftResult5 = controlGRVStep5Local.epaisseurSideLeftResult5,
            controlGRVForeignId = controlGRVStep5Local.foreignKey
        )






    /// ---> BUSINESS TO LOCAL


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

    fun controlGRVStepBusinessToLocal5(controlGRVStepBusiness: ControlGRVStepBusiness.ControlGRVStep5): ControlGRVStep5Local =
        ControlGRVStep5Local(
            reference = controlGRVStepBusiness.reference,
            epaisseurNA = controlGRVStepBusiness.epaisseurNA,
            epaisseurMinSideFront = controlGRVStepBusiness.epaisseurMinSideFront,
            epaisseurMinSideBack = controlGRVStepBusiness.epaisseurMinSideBack,
            epaisseurMinSideRight = controlGRVStepBusiness.epaisseurMinSideRight,
            epaisseurMinSideLeft = controlGRVStepBusiness.epaisseurMinSideLeft,
            epaisseurSideFrontResult1 = controlGRVStepBusiness.epaisseurSideFrontResult1,
            epaisseurSideFrontResult2 = controlGRVStepBusiness.epaisseurSideFrontResult2,
            epaisseurSideFrontResult3 = controlGRVStepBusiness.epaisseurSideFrontResult3,
            epaisseurSideFrontResult4 = controlGRVStepBusiness.epaisseurSideFrontResult4,
            epaisseurSideFrontResult5 = controlGRVStepBusiness.epaisseurSideFrontResult5,
            epaisseurSideBackResult1 = controlGRVStepBusiness.epaisseurSideBackResult1,
            epaisseurSideBackResult2 = controlGRVStepBusiness.epaisseurSideBackResult2,
            epaisseurSideBackResult3 = controlGRVStepBusiness.epaisseurSideBackResult3,
            epaisseurSideBackResult4 = controlGRVStepBusiness.epaisseurSideBackResult4,
            epaisseurSideBackResult5 = controlGRVStepBusiness.epaisseurSideBackResult5,
            epaisseurSideRightResult1 = controlGRVStepBusiness.epaisseurSideRightResult1,
            epaisseurSideRightResult2 = controlGRVStepBusiness.epaisseurSideRightResult2,
            epaisseurSideRightResult3 = controlGRVStepBusiness.epaisseurSideRightResult3,
            epaisseurSideRightResult4 = controlGRVStepBusiness.epaisseurSideRightResult4,
            epaisseurSideRightResult5 = controlGRVStepBusiness.epaisseurSideRightResult5,
            epaisseurSideLeftResult1 = controlGRVStepBusiness.epaisseurSideLeftResult1,
            epaisseurSideLeftResult2 = controlGRVStepBusiness.epaisseurSideLeftResult2,
            epaisseurSideLeftResult3 = controlGRVStepBusiness.epaisseurSideLeftResult3,
            epaisseurSideLeftResult4 = controlGRVStepBusiness.epaisseurSideLeftResult4,
            epaisseurSideLeftResult5 = controlGRVStepBusiness.epaisseurSideLeftResult5,
            foreignKey = controlGRVStepBusiness.controlGRVForeignId
        )
}