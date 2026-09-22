package com.example.domain.model

data class ControlGRVBusiness(
    var uid: Int? = null,
    var title: String? = null,
    val serialNumber: Int?,
    var currentStep: Int,
    var currentlyGoingOn: Boolean,
    var loaded: Boolean
)
