package com.example.myapplication.model

import android.os.Parcelable
import com.example.domain.utils.GRVControlStepEnum
import kotlinx.parcelize.Parcelize

@Parcelize
data class ControlGRV(
    var pageId: Int = 0,
    var uid: Int? = 0,
    var serialNumber: Int? = 0,
    var currentStep: GRVControlStepEnum = GRVControlStepEnum.STEP_0,
    var loaded: Boolean = false
) : Parcelable
