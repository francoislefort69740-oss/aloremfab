package com.example.myapplication.callback

interface MenuInterface {
    fun loadRegistrationFragment()
    fun loadGRVControlFragment()
    fun loadReportControlFragment()
    fun loadBuildControlFragment()
    fun createRegistrationFragment(noUserExist: Boolean? = null)
}