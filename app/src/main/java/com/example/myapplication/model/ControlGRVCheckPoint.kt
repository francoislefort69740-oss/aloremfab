package com.example.myapplication.model

import android.text.InputType

sealed class ControlGRVCheckPoint{

    data class EditableCheckPoint(
        var name: String = "",
        var title: String = "",
        val isEnable: Boolean = true,
        val inputType: Int = InputType.TYPE_CLASS_TEXT
    ): ControlGRVCheckPoint()
}