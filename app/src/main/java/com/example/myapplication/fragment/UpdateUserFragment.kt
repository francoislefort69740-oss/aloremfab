package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.callback.RegistrationInterface
import com.example.myapplication.utils.REGISTRATION_UPDATE_USER_TAG
import com.example.myapplication.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class UpdateUserFragment: BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_update_user_registration

    private val viewModel: MainViewModel by viewModel()

    override fun getBody(view: View, savedInstanceState: Bundle?) {
        view.findViewById<FloatingActionButton>(R.id.update_user_exit).setOnClickListener {
            mCallback?.loadRegistrationFragment()
        }

        arguments?.getInt(ACTIVE_ID)?.let { activeId ->
            viewModel.getUser(activeId)
            getObservations(view = view)

            view.findViewById<Button>(R.id.button_validate_update_user).setOnClickListener {
                viewModel.updateUser(
                    name = view.findViewById<EditText>(R.id.nameInput_update_user).text.toString(),
                    firstName = view.findViewById<EditText>(R.id.forenameInput_update_user).text.toString(),
                    email = view.findViewById<EditText>(R.id.emailInput_update_user).text.toString(),
                    uid = activeId
                )
            }
        }
    }

    private fun getObservations(view: View){
        viewModel.getUserLiveData().observe(this){ user ->
            view.findViewById<TextView>(R.id.emailTitle_update_user).text = user.email
            view.findViewById<TextView>(R.id.forenameTitle_update_user).text = user.firstName
            view.findViewById<TextView>(R.id.nameTitle_update_user).text = user.lastName

            view.findViewById<ImageView>(R.id.name_drag_update_user).setOnClickListener {
                view.findViewById<EditText>(R.id.nameInput_update_user).setText(user.lastName)
            }

            view.findViewById<ImageView>(R.id.firstname_drag_update_user).setOnClickListener {
                view.findViewById<EditText>(R.id.forenameInput_update_user).setText(user.firstName)
            }

            view.findViewById<ImageView>(R.id.email_drag_update_user).setOnClickListener {
                view.findViewById<EditText>(R.id.emailInput_update_user).setText(user.email)
            }

            viewModel.getUserRegistrationNameError().observe(this){
                if (it) {
                    view.findViewById<TextView>(R.id.error_update_user).text = getString(R.string.name_field_empty_create_user)
                    view.findViewById<TextView>(R.id.error_update_user).visibility = View.VISIBLE
                }
            }

            viewModel.getUserRegistrationForNameError().observe(this){
                if (it) {
                    view.findViewById<TextView>(R.id.error_update_user).text = getString(R.string.forname_field_empty_create_user)
                    view.findViewById<TextView>(R.id.error_update_user).visibility = View.VISIBLE
                }
            }

            viewModel.getUserRegistrationEmailError().observe(this){
                if (it) {
                    view.findViewById<TextView>(R.id.error_update_user).text = getString(R.string.email_field_empty_create_user)
                    view.findViewById<TextView>(R.id.error_update_user).visibility = View.VISIBLE
                }
            }

            viewModel.updateUserLiveData().observe(this){
                mCallback?.loadRegistrationFragment()
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
        fun newInstance(activeId: Int?) =
            UpdateUserFragment().apply {
                arguments = Bundle().apply {
                    activeId?.let { putInt(ACTIVE_ID, it) }
                }
            }
        const val TAG = REGISTRATION_UPDATE_USER_TAG
        const val ACTIVE_ID = "ACTIVE_ID"
    }
}