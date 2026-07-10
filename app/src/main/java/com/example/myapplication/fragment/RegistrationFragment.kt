package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.callback.RegistrationInterface
import com.example.myapplication.utils.REGISTRATION_TAG
import com.example.myapplication.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue


class RegistrationFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_registration

    private val viewModel: MainViewModel by viewModel()

    companion object {
        fun newInstance() = RegistrationFragment()
        const val TAG = REGISTRATION_TAG
    }

    override fun getBody(view: View, savedInstanceState: Bundle?) {
        view.findViewById<FloatingActionButton>(R.id.registration_exit).setOnClickListener {
            mCallback?.loadMenuFragment()
        }
    }

    /**
     *  LIFE CYCLE
     */

    private var mCallback: RegistrationInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try { mCallback = activity as RegistrationInterface }
        catch (e: ClassCastException) { throw ClassCastException("$e must implemented MainInterface") }
    }
}