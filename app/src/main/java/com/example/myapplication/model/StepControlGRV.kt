package com.example.myapplication.model

sealed class StepControlGRV{
    data class Step0ControlGRV(
        var reference: Int? = null,
        var reportNumber: Int? = null,
        var customer: String? = null,
        var customerSerialNumber: Int? = null,
        var serialNumberAlorem: Int? = null,
        var type: String? = null,
        var controlGRVForeignId: Int? = null
    ) : StepControlGRV()

    data class Step1ControlGRV(
        val reference: Int? = null,
        var fabricationPlateAdr: Boolean? = null,
        var aloremPlate: Boolean? = null,
        var bookletPouch: Boolean? = null,
        var userManual: Boolean? = null,
        var instructionOfUse: Boolean? = null,
        var certificatesADR: Boolean? = null,
        var groundingAdhesive: Boolean? = null,
        var conformityCertificateMarking: Boolean? = null,
        var controlGRVForeignId: Int? = null
    ) : StepControlGRV()

    data class Step2ControlGRV(
        val reference: Int? = null,
        var tare: Int? = null,
        var material: String? = null,
        var capacity20: Int? = null,
        var grossMass: Int? = null,
        var fabricationDate: String? = null,
        var shellThickness: Int? = null,
        var pictogramStacking: Boolean? = null,
        var weightStacking: Int? = null,
        var controlGRVForeignId: Int? = null
    ) : StepControlGRV()

    data class Step3ControlGRV(
        val reference: Int? = null,
        var bottomRetentionFace: Int? = null,
        var bottomRetentionRight: Int? = null,
        var bottomRetentionLeft: Int? = null,
        var bottomRetentionBehind: Int? = null,
        var upperRetention: Int? = null,
        var liftingRings: Int? = null,
        var forkliftPass: Int? = null,
        var dashboard: Int? = null,
        var unauthorizedRepair: Boolean = false,
        var controlGRVForeignId: Int? = null
    ) : StepControlGRV()

    data class Step4ControlGRV(
        val reference: Int? = null,
        var internalNA: Boolean = false,
        var internalOK: Boolean? = null,
        var internalClean: Boolean = false,
        var internalObjectInside: Boolean = false,
        var internalPollution: Boolean = false,
        var controlGRVForeignId: Int? = null
    ) : StepControlGRV()

}
