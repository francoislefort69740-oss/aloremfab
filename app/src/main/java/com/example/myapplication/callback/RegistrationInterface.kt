package com.example.myapplication.callback

interface RegistrationInterface {
    fun loadRegistrationFragment()
    fun loadMenuFragment()
    fun createRegistrationFragment(noUserExist: Boolean?)
    fun loadUpdateUserFragment(activeId: Int? = null)
}