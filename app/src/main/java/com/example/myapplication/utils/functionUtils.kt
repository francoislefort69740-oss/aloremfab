package com.example.myapplication.utils

import android.content.Context
import com.example.myapplication.model.ControlGRVCheckPoint

fun returnCheckPointForCheckBox(context: Context, resId: Int, list: List<ControlGRVCheckPoint>): Boolean? {
    return if ((list.find {
            (it as ControlGRVCheckPoint.CheckBoxCheckPoint).name == context.getString(resId)
        } as ControlGRVCheckPoint.CheckBoxCheckPoint).isChecked == true) (list.find {
        (it as ControlGRVCheckPoint.CheckBoxCheckPoint).name == context.getString(resId)
    } as ControlGRVCheckPoint.CheckBoxCheckPoint).value else null
}

fun returnCheckPointForEditableString(context: Context, resId: Int, list: List<ControlGRVCheckPoint>): String {
    return (list.find {
        (it as ControlGRVCheckPoint.EditableCheckPoint).name == context.getString(resId)
    } as ControlGRVCheckPoint.EditableCheckPoint).value
}

fun returnCheckPointForEditableInt(context: Context, resId: Int, list: List<ControlGRVCheckPoint>): Int? {
    return (list.find {
        (it as ControlGRVCheckPoint.EditableCheckPoint).name == context.getString(resId)
    } as ControlGRVCheckPoint.EditableCheckPoint).value.toIntOrNull()
}