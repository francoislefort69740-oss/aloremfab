package com.example.myapplication.model

sealed class ControlGRVCheckPoint{

    data class EditableCheckPoint(
        var name: String = "",
        var title: String = ""
    ): ControlGRVCheckPoint()
}