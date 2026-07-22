package com.example.myapplication.callback

import com.example.myapplication.model.ControlGRV

interface ChildViewPagerGRVInterface {
    fun createNewPage(serialNumber: Int = 0)
    fun getAddingPage(newList: List<ControlGRV>? = null)
    fun saveControl()
}