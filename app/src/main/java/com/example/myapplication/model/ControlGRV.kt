package com.example.myapplication.model

data class ControlGRV(
    var pageId: Int = 0,
    val uid: Int? = 0,
    val serialNumber: Int? = 0,
    var currentStep: StepControlGRV? = null
)
