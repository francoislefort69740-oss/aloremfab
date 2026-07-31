package com.example.myapplication.model

import android.text.InputType

sealed class ControlGRVCheckPoint{

    data class EditableCheckPoint(
        var name: String = "",
        var value: String = "",
        val isEnable: Boolean = true,
        val inputType: Int = InputType.TYPE_CLASS_TEXT
    ): ControlGRVCheckPoint()

    data class CheckBoxCheckPoint(
        var name: String = "",
        var value: Boolean? = null,
        var isChecked: Boolean? = null,
        val isEnable: Boolean = true
    ): ControlGRVCheckPoint()
}