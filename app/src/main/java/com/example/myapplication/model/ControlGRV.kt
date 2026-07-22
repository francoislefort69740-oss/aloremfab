package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ControlGRV(
    var pageId: Int = 0,
    val uid: Int? = 0,
    val serialNumber: Int? = 0,
    var currentStep: Int = 0,
    var loaded: Boolean = false
) : Parcelable
