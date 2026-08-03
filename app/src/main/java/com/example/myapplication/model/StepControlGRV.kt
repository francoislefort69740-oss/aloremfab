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

}
