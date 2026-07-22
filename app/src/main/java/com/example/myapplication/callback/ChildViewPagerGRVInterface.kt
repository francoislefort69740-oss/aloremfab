package com.example.myapplication.callback

interface ChildViewPagerGRVInterface {
    fun createNewPage()
    fun deleteControl(pos: Int)
    fun saveControl()
    fun injectControl(serialNumber: Int)
}