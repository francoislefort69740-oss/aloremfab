package com.example.domain.model

data class ControlGRVBusiness(
    var uid: Int? = null,
    val serialNumber: Int?,
    var currentStep: Int,
    var currentlyGoingOn: Boolean,
    var loaded: Boolean
)
