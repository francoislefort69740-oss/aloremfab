package com.example.myapplication.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.callback.RegistrationInterface
import com.example.myapplication.fragment.MainFragment
import com.example.myapplication.fragment.MenuFragment
import com.example.myapplication.fragment.RegistrationFragment
import com.example.myapplication.utils.MAIN_TAG

class MainActivity : BaseActivity(), RegistrationInterface {

    override fun getCurrentTag(): String = MAIN_TAG
    override fun getActivityId(): Int = R.layout.activity_main
    override fun getViewId(): Int = R.id.main
    override fun getFragmentLayout(): Int = R.id.main_activity_fragmentlayout
    override fun getFragment(): Fragment = MenuFragment.newInstance()

    override fun childBody(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
    }

    // INTERFACE

    override fun loadRegistrationFragment() {
        buildInterface(RegistrationFragment.TAG)
    }

    override fun loadMenuFragment() {
        buildInterface(MenuFragment.TAG)
    }


}