package com.example.myapplication.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_main

    private val viewModel: MainViewModel by viewModel()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun getBody(view: View, savedInstanceState: Bundle?) {
        val userNameTextView = view.findViewById<TextView>(R.id.userTitle_menu)
        val userEditText = view.findViewById<TextView>(R.id.userSpace)
        val userButton = view.findViewById<TextView>(R.id.userChangeBtn_menu)

        var firstName = ""
        var email = ""
        var name = ""

        viewModel.getUser(1)

        userButton.setOnClickListener {
            firstName = userEditText.text.toString()
            viewModel.updateUser(firstName = firstName, name = name, email = email)
        }

        viewModel.getUserLiveData().observe(this){ response ->
            userNameTextView.text = response.firstName
            name = response.lastName
            email = response.email
        }

        viewModel.updateUserLiveData().observe(this){ response ->
            if (response) {
                viewModel.getUser(1)
            }
        }
    }
}