package com.example.myapplication.callback

interface MenuInterface {
    fun loadRegistrationFragment()
    fun loadGRVControlFragment()
    fun loadReportControlFragment()
    fun createRegistrationFragment(noUserExist: Boolean? = null)
}