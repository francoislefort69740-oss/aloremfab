package com.example.data.mapper

import com.example.data.api.modele.RemoteGRVControl
import com.example.data.api.modele.RemoteGRVControlStep
import com.example.data.api.modele.RemoteMessage
import com.example.domain.model.BusinessRemoteListGRVControl
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.RemoteMessageBusiness

object RemoteControlGRVMapper {

    fun getAllRemoteControlGRVToBusiness(remoteListGRVControl: List<RemoteGRVControl>): BusinessRemoteListGRVControl {
        val result = mutableListOf<ControlGRVBusiness>()
        remoteListGRVControl.forEach() {
            result.add(remoteControlGRVToBusiness(it))
        }
        return BusinessRemoteListGRVControl(list = result)
    }

    fun remoteControlGRVToBusiness(remoteControlGRV: RemoteGRVControl): ControlGRVBusiness = ControlGRVBusiness(
        uid = remoteControlGRV.uid,
        title = remoteControlGRV.title,
        serialNumber = remoteControlGRV.serialNumber,
        currentStep = remoteControlGRV.currentStep,
        currentlyGoingOn = remoteControlGRV.currentlyGoingOn,
        loaded = remoteControlGRV.loaded,
    )

    fun businessControlGRVToRemote(controlGRVStepBusiness: ControlGRVStepBusiness.ControlGRVAllStep, controlGRVBusiness: ControlGRVBusiness): RemoteGRVControl {

        val reference = requireNotNull(controlGRVBusiness.serialNumber) { "Le numéro de série de la commande est obligatoire." }

        return RemoteGRVControl(
            uid = controlGRVBusiness.uid,
            title = controlGRVBusiness.title,
            serialNumber = reference,
            currentStep = controlGRVBusiness.currentStep,
            loaded = controlGRVBusiness.loaded,
            currentlyGoingOn = controlGRVBusiness.currentlyGoingOn,
            steps = businessStepToStepRemote(controlGRVStepBusiness, reference)
        )
    }

    fun businessStepToStepRemote(
        allSteps: ControlGRVStepBusiness.ControlGRVAllStep,
        reference: Int
    ): List<RemoteGRVControlStep> {

        val s0 = allSteps.controlGRVStep0
        val s1 = allSteps.controlGRVStep1
        val s2 = allSteps.controlGRVStep2
        val s3 = allSteps.controlGRVStep3
        val s4 = allSteps.controlGRVStep4
        val s5 = allSteps.controlGRVStep5
        val s6 = allSteps.controlGRVStep6

        return listOfNotNull(

            // STEP 0
            s0?.let {
                RemoteGRVControlStep(
                    step = 0,
                    reference = reference,
                    reportNumber = it.reportNumber,
                    customer = it.customer,
                    customerSerialNumber = it.customerSerialNumber,
                    serialNumberAlorem = it.serialNumberAlorem,
                    type = it.type
                )
            },

            // STEP 1
            s1?.let {
                RemoteGRVControlStep(
                    step = 1,
                    reference = reference,
                    fabricationPlateAdr = it.fabricationPlateAdr,
                    aloremPlate = it.aloremPlate,
                    bookletPouch = it.bookletPouch,
                    userManual = it.userManual,
                    instructionOfUse = it.instructionOfUse,
                    certificatesADR = it.certificatesADR,
                    groundingAdhesive = it.groundingAdhesive,
                    conformityCertificateMarking =
                        it.conformityCertificateMarking
                )
            },

            // STEP 2
            s2?.let {
                RemoteGRVControlStep(
                    step = 2,
                    reference = reference,
                    marquePrincipale = it.marquePrincipale,
                    tare = it.tare,
                    material = it.material,
                    capacity20 = it.capacity20,
                    grossMass = it.grossMass,
                    fabricationDate = it.fabricationDate,
                    shellThickness = it.shellThickness,
                    pictogramStacking = it.pictogramStacking,
                    weightStacking = it.weightStacking
                )
            },

            // STEP 3
            s3?.let {
                RemoteGRVControlStep(
                    step = 3,
                    reference = reference,
                    bottomRetentionFace = it.bottomRetentionFace,
                    bottomRetentionRight = it.bottomRetentionRight,
                    bottomRetentionLeft = it.bottomRetentionLeft,
                    bottomRetentionBehind = it.bottomRetentionBehind,
                    upperRetention = it.upperRetention,
                    liftingRings = it.liftingRings,
                    forkliftPass = it.forkliftPass,
                    dashboard = it.dashboard,
                    unauthorizedRepair = it.unauthorizedRepair
                )
            },

            // STEP 4
            s4?.let {
                RemoteGRVControlStep(
                    step = 4,
                    reference = reference,
                    internalNA = it.internalNA,
                    internalOK = it.internalOK,
                    internalClean = it.internalClean,
                    internalObjectInside = it.internalObjectInside,
                    internalPollution = it.internalPollution
                )
            },

            // STEP 5
            s5?.let {
                RemoteGRVControlStep(
                    step = 5,
                    reference = reference,
                    epaisseurNA = it.epaisseurNA,
                    epaisseurMinSideFront = it.epaisseurMinSideFront,
                    epaisseurMinSideBack = it.epaisseurMinSideBack,
                    epaisseurMinSideRight = it.epaisseurMinSideRight,
                    epaisseurMinSideLeft = it.epaisseurMinSideLeft,

                    epaisseurSideFrontResult1 = it.epaisseurSideFrontResult1,
                    epaisseurSideFrontResult2 = it.epaisseurSideFrontResult2,
                    epaisseurSideFrontResult3 = it.epaisseurSideFrontResult3,
                    epaisseurSideFrontResult4 = it.epaisseurSideFrontResult4,
                    epaisseurSideFrontResult5 = it.epaisseurSideFrontResult5,

                    epaisseurSideBackResult1 = it.epaisseurSideBackResult1,
                    epaisseurSideBackResult2 = it.epaisseurSideBackResult2,
                    epaisseurSideBackResult3 = it.epaisseurSideBackResult3,
                    epaisseurSideBackResult4 = it.epaisseurSideBackResult4,
                    epaisseurSideBackResult5 = it.epaisseurSideBackResult5,

                    epaisseurSideRightResult1 = it.epaisseurSideRightResult1,
                    epaisseurSideRightResult2 = it.epaisseurSideRightResult2,
                    epaisseurSideRightResult3 = it.epaisseurSideRightResult3,
                    epaisseurSideRightResult4 = it.epaisseurSideRightResult4,
                    epaisseurSideRightResult5 = it.epaisseurSideRightResult5,

                    epaisseurSideLeftResult1 = it.epaisseurSideLeftResult1,
                    epaisseurSideLeftResult2 = it.epaisseurSideLeftResult2,
                    epaisseurSideLeftResult3 = it.epaisseurSideLeftResult3,
                    epaisseurSideLeftResult4 = it.epaisseurSideLeftResult4,
                    epaisseurSideLeftResult5 = it.epaisseurSideLeftResult5
                )
            },

            // STEP 6
            s6?.let {
                RemoteGRVControlStep(
                    step = 6,
                    reference = reference,
                    etancheiteOK1 = it.etancheiteOK1,
                    etancheiteDate1 = it.etancheiteDate1,
                    etancheiteBar1 = it.etancheiteBar1,
                    etancheiteOK2 = it.etancheiteOK2,
                    etancheiteDate2 = it.etancheiteDate2,
                    etancheiteBar2 = it.etancheiteBar2
                )
            }
        )
    }


    fun getMessage(remoteMessage: RemoteMessage): RemoteMessageBusiness = RemoteMessageBusiness(
        message = remoteMessage.message
    )
}