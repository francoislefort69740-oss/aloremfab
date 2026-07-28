package com.example.domain.model

sealed class ControlGRVStepBusiness{

    data class ControlGRVStep0(
        val reference: Int,
        var reportNumber: Int,
        var customer: String,
        var customerSerialNumber: Int,
        var serialNumberAlorem: Int,
        var type: String,
        var controlGRVForeignId: Int
    ) : ControlGRVStepBusiness()



}
