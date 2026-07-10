package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.callback.RegistrationInterface
import com.example.myapplication.utils.REGISTRATION_TAG
import com.example.myapplication.viewmodel.MainViewModel
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
        val i = 0
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