package com.example.myapplication.utils

import com.example.domain.utils.GRVControlStepEnum

enum class CheckPointHolderEnum(val type: Int) {
    VIEW_TYPE_EDITABLE(0),
    VIEW_TYPE_CHECKBOX(1),
    VIEW_TYPE_FOUR_STATE(2),
    VIEW_TYPE_SINGLE_STATE(3);

    companion object {

        fun getStep(stepNumber: Int): CheckPointHolderEnum {
            return entries.find { it.type == stepNumber } ?: VIEW_TYPE_EDITABLE
        }

        fun getStepNumber(step: CheckPointHolderEnum): Int {
            return step.type
        }

    }
}