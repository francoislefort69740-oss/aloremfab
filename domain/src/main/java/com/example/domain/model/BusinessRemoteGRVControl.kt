package com.example.domain.model

data class BusinessRemoteGRVControl(
    val uid: Int?,
    val title: String?,
    val serialNumber: Int?,
    val currentStep: Int,
    val currentlyGoingOn: Boolean,
    val loaded: Boolean,
    val steps: List<BusinessRemoteGRVControlStep>?
)
