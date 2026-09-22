package com.example.myapplication.model

import android.os.Parcelable
import androidx.appcompat.widget.DialogTitle
import com.example.domain.utils.GRVControlStepEnum
import kotlinx.parcelize.Parcelize

@Parcelize
data class ControlGRV(
    var pageId: Int = 0,
    var title: String? = null,
    var uid: Int? = 0,
    var serialNumber: Int? = 0,
    var currentStep: GRVControlStepEnum = GRVControlStepEnum.STEP_0,
    var loaded: Boolean = false
) : Parcelable
