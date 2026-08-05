package com.example.myapplication.utils

import android.content.Context
import com.example.myapplication.model.ControlGRVCheckPoint

fun returnCheckPointForCheckBox(context: Context, resId: Int, list: List<ControlGRVCheckPoint>): Boolean? {
    val checkpoint = list.filterIsInstance<ControlGRVCheckPoint.CheckBoxCheckPoint>()
        .find { it.name == context.getString(resId) }
    return if (checkpoint?.isChecked == true) checkpoint.value else null
}

fun returnCheckPointForEditableString(context: Context, resId: Int, list: List<ControlGRVCheckPoint>): String {
    val checkpoint = list.filterIsInstance<ControlGRVCheckPoint.EditableCheckPoint>()
        .find { it.name == context.getString(resId) }
    return checkpoint?.value ?: ""
}

fun returnCheckPointForEditableInt(context: Context, resId: Int, list: List<ControlGRVCheckPoint>): Int? {
    val checkpoint = list.filterIsInstance<ControlGRVCheckPoint.EditableCheckPoint>()
        .find { it.name == context.getString(resId) }
    return checkpoint?.value?.toIntOrNull()
}