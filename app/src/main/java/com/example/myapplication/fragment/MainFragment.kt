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
        val userNameTextView = view.findViewById<TextView>(R.id.textView)
        viewModel.getUser(0)
        viewModel.getUserLiveData().observe(this){ response ->
            userNameTextView.text = response.firstName
        }
    }

    private fun testDatabase() {

    }
}