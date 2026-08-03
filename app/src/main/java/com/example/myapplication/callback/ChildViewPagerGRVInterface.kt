package com.example.myapplication.callback

import com.example.domain.utils.GRVControlStepEnum
import com.example.myapplication.model.ControlGRV

interface ChildViewPagerGRVInterface {
    fun createNewPage(serialNumber: Int = 0, currentStep: GRVControlStepEnum = GRVControlStepEnum.STEP_0)
    fun getAddingPage(newList: List<ControlGRV>? = null)
    fun saveControl()
}