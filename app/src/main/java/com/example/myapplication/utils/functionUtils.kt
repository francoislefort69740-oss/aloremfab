package com.example.myapplication.utils

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRVCheckPoint
import com.example.myapplication.recycler.CheckBoxCheckPointHolder
import com.example.myapplication.recycler.EditableCheckPointHolder
import com.example.myapplication.recycler.FourStateCheckPointHolder
import com.example.myapplication.recycler.SingleCheckCheckPointHolder
import com.example.myapplication.recycler.StepGRVListHolder
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

fun returnCheckPointForSingleCheck(context: Context, resId: Int, list: List<ControlGRVCheckPoint>): Boolean {
    val checkpoint = list.filterIsInstance<ControlGRVCheckPoint.SingleCheckCheckPoint>()
        .find { it.name == context.getString(resId) }
    return checkpoint?.value ?: false
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
            is ControlGRVCheckPoint.SingleCheckCheckPoint ->
                Log.d(
                    CHECK_GRV,
                    "$index : title='${checkpoint.name}' value='${checkpoint.value}'"
                )
        }
    }
    Log.d(CHECK_GRV, "completed = $result")
}



// Recycler View Holder



fun returnHolder(parent: ViewGroup, viewType: CheckPointHolderEnum, inflater: LayoutInflater): StepGRVListHolder = when(viewType) {
    CheckPointHolderEnum.VIEW_TYPE_EDITABLE -> {
        EditableCheckPointHolder(inflater.inflate(R.layout.item_grv_data_card_editable, parent, false))
    }
    CheckPointHolderEnum.VIEW_TYPE_CHECKBOX -> {
        CheckBoxCheckPointHolder(inflater.inflate(R.layout.item_grv_data_card_checkable, parent, false))
    }
    CheckPointHolderEnum.VIEW_TYPE_FOUR_STATE -> {
        FourStateCheckPointHolder(inflater.inflate(R.layout.item_grv_data_card_four_state, parent, false))
    }
    CheckPointHolderEnum.VIEW_TYPE_SINGLE_STATE -> {
        SingleCheckCheckPointHolder(inflater.inflate(R.layout.item_grv_data_card_single_state, parent, false))
    }
}

fun returnItemViewType(item: ControlGRVCheckPoint): Int = when(item) {
    is ControlGRVCheckPoint.EditableCheckPoint ->
        CheckPointHolderEnum.getStepNumber(CheckPointHolderEnum.VIEW_TYPE_EDITABLE)
    is ControlGRVCheckPoint.CheckBoxCheckPoint ->
        CheckPointHolderEnum.getStepNumber(CheckPointHolderEnum.VIEW_TYPE_CHECKBOX)
    is ControlGRVCheckPoint.FourStateCheckPoint ->
        CheckPointHolderEnum.getStepNumber(CheckPointHolderEnum.VIEW_TYPE_FOUR_STATE)
    is ControlGRVCheckPoint.SingleCheckCheckPoint ->
        CheckPointHolderEnum.getStepNumber(CheckPointHolderEnum.VIEW_TYPE_SINGLE_STATE)
}