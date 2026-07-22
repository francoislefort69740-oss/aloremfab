package com.example.domain.model

data class ControlGRVBusiness(
    var uid: Int? = null,
    val serialNumber: Int?,
    val currentStep: Int,
    val currentlyGoingOn: Boolean,
    var loaded: Boolean
)
