package com.example.myapplication.model

sealed class StepControlGRV{
    data class Step0ControlGRV(
        val reference: Int,
        var reportNumber: Int,
        var customer: String,
        var customerSerialNumber: Int,
        var serialNumberAlorem: Int,
        var type: String,
        var controlGRVForeignId: Int
    ) : StepControlGRV()

}
