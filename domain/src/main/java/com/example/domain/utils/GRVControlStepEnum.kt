package com.example.domain.utils

enum class GRVControlStepEnum(val stepNumber: Int) {
    STEP_0(0),
    STEP_1(1),
    STEP_2(2),
    STEP_3(3),
    STEP_4(4),
    STEP_5(5),
    STEP_6(6);

    fun next(): GRVControlStepEnum = entries.getOrNull(ordinal + 1) ?: STEP_0

    fun back(): GRVControlStepEnum = entries.getOrNull(ordinal - 1) ?: STEP_0

    companion object {

        fun getStep(stepNumber: Int): GRVControlStepEnum {
            return entries.find { it.stepNumber == stepNumber } ?: STEP_0
        }

        fun getStepNumber(step: GRVControlStepEnum): Int {
            return step.stepNumber
        }

    }
}