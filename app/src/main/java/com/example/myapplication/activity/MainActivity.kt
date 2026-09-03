package com.example.myapplication.activity

import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.callback.GRVControlInterface
import com.example.myapplication.callback.MenuInterface
import com.example.myapplication.callback.RegistrationInterface
import com.example.myapplication.callback.ReportControlInterface
import com.example.myapplication.fragment.CreateUserFragment
import com.example.myapplication.fragment.GRVMainFragment
import com.example.myapplication.fragment.MenuFragment
import com.example.myapplication.fragment.RegistrationFragment
import com.example.myapplication.fragment.ReportFragment
import com.example.myapplication.fragment.UpdateUserFragment
import com.example.myapplication.utils.MAIN_TAG

class MainActivity : BaseActivity(),
    RegistrationInterface,
    GRVControlInterface,
    MenuInterface,
    ReportControlInterface
{

    override fun getCurrentTag(): String = MAIN_TAG
    override fun getActivityId(): Int = R.layout.activity_main
    override fun getViewId(): Int = R.id.main
    override fun getFragmentLayout(): Int = R.id.main_activity_fragmentlayout
    override fun getFragment(): Fragment = MenuFragment.newInstance()

    override fun childBody(savedInstanceState: Bundle?) {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, 0, 0, 0)
            insets
        }
    }

    // INTERFACE

    override fun loadRegistrationFragment() {
        buildInterface(RegistrationFragment.TAG)
    }

    override fun loadGRVControlFragment() {
        buildInterface(GRVMainFragment.TAG)
    }

    override fun loadReportControlFragment() {
        buildInterface(ReportFragment.TAG)
    }

    override fun loadMenuFragment() {
        buildInterface(MenuFragment.TAG)
    }

    override fun createRegistrationFragment(noUserExist: Boolean?) {
        buildInterface(CreateUserFragment.TAG, obj = noUserExist)
    }

    override fun loadUpdateUserFragment(activeId: Int?) {
        buildInterface(UpdateUserFragment.TAG, obj = activeId)
    }


}