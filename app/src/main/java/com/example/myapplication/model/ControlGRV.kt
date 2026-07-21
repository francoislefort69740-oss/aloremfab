package com.example.myapplication.model

data class ControlGRV(
    val pageId: Int,
    val uid: Int = 0,
    val serialNumber: Int = 0,
    var currentStep: StepControlGRV? = null
)
