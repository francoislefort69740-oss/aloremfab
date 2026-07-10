package com.example.myapplication.utils

import android.util.Log
import androidx.fragment.app.FragmentManager
import com.example.myapplication.fragment.MainFragment
import com.example.myapplication.fragment.MenuFragment
import com.example.myapplication.fragment.RegistrationFragment

fun fragmentManagerBusinessByTAG(tag: String, supportFragmentManager: FragmentManager, fragmentLayout: Int, obj: Any? = null) = when(tag) {
    MAIN_TAG -> supportFragmentManager.beginTransaction().replace(fragmentLayout, MenuFragment.newInstance(), MENU_TAG).commit()
    REGISTRATION_TAG -> supportFragmentManager.beginTransaction().replace(fragmentLayout, RegistrationFragment.newInstance(), REGISTRATION_TAG).commit()
    else -> Log.i("FAIL", "Fragment manager error : no Fragment to load")
}