package com.example.myapplication.utils

import android.util.Log
import androidx.fragment.app.FragmentManager
import com.example.myapplication.fragment.CreateUserFragment
import com.example.myapplication.fragment.MainFragment
import com.example.myapplication.fragment.MenuFragment
import com.example.myapplication.fragment.RegistrationFragment
import com.example.myapplication.fragment.UpdateUserFragment

fun fragmentManagerBusinessByTAG(tag: String, supportFragmentManager: FragmentManager, fragmentLayout: Int, obj: Any? = null) = when(tag) {
    MENU_TAG -> supportFragmentManager.beginTransaction().replace(fragmentLayout, MenuFragment.newInstance(), MENU_TAG).commit()
    REGISTRATION_TAG -> supportFragmentManager.beginTransaction().replace(fragmentLayout, RegistrationFragment.newInstance(), REGISTRATION_TAG).commit()
    REGISTRATION_CREATE_USER_TAG ->
        if (obj is Boolean) {
            supportFragmentManager.beginTransaction()
                .replace(fragmentLayout, CreateUserFragment.newInstance(obj), REGISTRATION_CREATE_USER_TAG).commit()
        }
        else Log.i("FAIL", "Fragment manager error : no Fragment to load")
    REGISTRATION_UPDATE_USER_TAG ->
        if (obj is Int) {
            supportFragmentManager.beginTransaction()
                .replace(fragmentLayout, UpdateUserFragment.newInstance(obj), REGISTRATION_UPDATE_USER_TAG).commit()
        }
        else Log.i("FAIL", "Fragment manager error : no Fragment to load")
    MAIN_TAG -> supportFragmentManager.beginTransaction().replace(fragmentLayout, MainFragment.newInstance(), MAIN_TAG).commit()
    else -> Log.i("FAIL", "Fragment manager error : no Fragment to load")
}