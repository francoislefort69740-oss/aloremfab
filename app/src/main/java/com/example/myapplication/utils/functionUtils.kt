package com.example.myapplication.utils

import android.content.Context
import android.util.Log
import com.example.myapplication.model.ControlGRVCheckPoint
import org.koin.core.qualifier.named

fun returnCheckPointForFourState(context: Context, resId: Int, list: List<ControlGRVCheckPoint>): Int? {
    val checkpoint = list.filterIsInstance<ControlGRVCheckPoint.FourStateCheckPoint>()
        .find { it.name == context.getString(resId) }
    return if (checkpoint?.isChecked == true) checkpoint.value else null
}

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

fun getCheckLogControlGRV(list: List<ControlGRVCheckPoint>?, result: Boolean) {
    Log.d("CHECK_GRV", " ")
    Log.d("CHECK_GRV", " ")
    Log.d("CHECK_GRV", "================== NOUVEAU TEST ==================")
    Log.d("CHECK_GRV", " ")
    list?.forEachIndexed { index, checkpoint ->
        when (checkpoint) {
            is ControlGRVCheckPoint.EditableCheckPoint ->
                Log.d(
                    CHECK_GRV,
                    "$index : title='${checkpoint.name}' value='${checkpoint.value}'"
                )

            is ControlGRVCheckPoint.CheckBoxCheckPoint ->
                Log.d(
                    CHECK_GRV,
                    "$index : title='${checkpoint.name}' value='${checkpoint.value}' isChecked='${checkpoint.isChecked}"
                )
            is ControlGRVCheckPoint.FourStateCheckPoint ->
                Log.d(
                    CHECK_GRV,
                    "$index : title='${checkpoint.name}' value='${checkpoint.value}' isChecked='${checkpoint.isChecked}"
                )
        }
    }
    Log.d(CHECK_GRV, "completed = $result")
}