package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.domain.model.ErrorBusiness
import com.example.myapplication.R
import com.example.myapplication.callback.RegistrationInterface
import com.example.myapplication.utils.REGISTRATION_CREATE_USER_TAG
import com.example.myapplication.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class CreateUserFragment: BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_create_user

    private val viewModel: MainViewModel by viewModel()

    override fun getBody(view: View, savedInstanceState: Bundle?) {

        if (arguments?.getBoolean(EMPTY_USER) == true) {
            view.findViewById<FloatingActionButton>(R.id.create_user_exit).visibility = View.GONE
        }

        view.findViewById<View>(R.id.create_user_exit).setOnClickListener {
            mCallback?.loadRegistrationFragment()
        }

        view.findViewById<Button>(R.id.button_validate_create_user).setOnClickListener {
            viewModel.createUser(
                name = view.findViewById<EditText>(R.id.nameInput_create_user).text.toString(),
                firstName = view.findViewById<EditText>(R.id.forenameInput_create_user).text.toString(),
                email = view.findViewById<EditText>(R.id.emailInput_create_user).text.toString()
            )
        }

        viewModel.createUserLiveData().observe(this){
            if (it) mCallback?.loadRegistrationFragment()
        }

        viewModel.getUserRegistrationError().observe(this){
            if (it) view.findViewById<TextView>(R.id.error_create_user).visibility = View.VISIBLE
        }

        viewModel.getUserRegistrationNameError().observe(this){
            if (it) {
                view.findViewById<TextView>(R.id.error_create_user).text = getString(R.string.name_field_empty_create_user)
                view.findViewById<TextView>(R.id.error_create_user).visibility = View.VISIBLE
            }
        }

        viewModel.getUserRegistrationForNameError().observe(this){
            if (it) {
                view.findViewById<TextView>(R.id.error_create_user).text = getString(R.string.forname_field_empty_create_user)
                view.findViewById<TextView>(R.id.error_create_user).visibility = View.VISIBLE
            }
        }

        viewModel.getUserRegistrationEmailError().observe(this){
            if (it) {
                view.findViewById<TextView>(R.id.error_create_user).text = getString(R.string.email_field_empty_create_user)
                view.findViewById<TextView>(R.id.error_create_user).visibility = View.VISIBLE
            }
        }
    }

    private var mCallback: RegistrationInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mCallback = activity as RegistrationInterface
        } catch (e: ClassCastException) {
            throw ClassCastException("$e must implement RegistrationInterface")
        }
    }
    companion object {
        fun newInstance(emptyUser: Boolean?) =
            CreateUserFragment().apply {
                arguments = Bundle().apply {
                    emptyUser?.let { putBoolean(EMPTY_USER, it) }
                }
            }

        const val TAG = REGISTRATION_CREATE_USER_TAG
        const val EMPTY_USER = "EMPTY_USER"
    }

}