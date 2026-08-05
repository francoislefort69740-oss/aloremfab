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

}
