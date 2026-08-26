package com.example.myapplication.model

import android.text.InputType

sealed class ControlGRVCheckPoint{

    abstract fun duplicate(): ControlGRVCheckPoint

    data class EditableCheckPoint(
        var name: String = "",
        var value: String = "",
        val isEnable: Boolean = true,
        val inputType: Int = InputType.TYPE_CLASS_TEXT
    ): ControlGRVCheckPoint() {
        override fun duplicate() = copy()
    }

    data class CheckBoxCheckPoint(
        var name: String = "",
        var value: Boolean? = null,
        var isChecked: Boolean? = null,
        val isEnable: Boolean = true
    ): ControlGRVCheckPoint() {
        override fun duplicate() = copy()
    }

    data class FourStateCheckPoint(
        val name: String = "",
        var value: Int? = null,
        var isChecked: Boolean? = null,
        var secondLineIsVisible: Boolean = false,
        val isEnable: Boolean = true
    ) : ControlGRVCheckPoint() {
        override fun duplicate() = copy()
    }

    data class SingleCheckCheckPoint(
        val name: String = "",
        var value: Boolean = false,
        val isEnable: Boolean = true
    ) : ControlGRVCheckPoint(){
        override fun duplicate() = copy()
    }

}