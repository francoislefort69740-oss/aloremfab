package com.example.data.api.modele

import com.google.gson.annotations.SerializedName

data class RemoteGRVControl(
    @SerializedName("uid") val uid: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("serialNumber") val serialNumber: Int?,
    @SerializedName("currentStep") val currentStep: Int,
    @SerializedName("currentlyGoingOn") val currentlyGoingOn: Boolean,
    @SerializedName("loaded") val loaded: Boolean,
    @SerializedName("steps") val steps: List<RemoteGRVControlStep>?
)
