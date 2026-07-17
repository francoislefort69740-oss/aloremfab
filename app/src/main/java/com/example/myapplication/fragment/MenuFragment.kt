package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.callback.RegistrationInterface
import com.example.myapplication.recycler.RegistrationUserListAdapter
import com.example.myapplication.utils.MENU_TAG
import com.example.myapplication.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_menu

    private val viewModel: MainViewModel by viewModel()

    companion object {
        fun newInstance() = MenuFragment()
        const val TAG = MENU_TAG
    }

    override fun getBody(view: View, savedInstanceState: Bundle?) {

        view.findViewById<TextView>(R.id.userChangeBtn_menu).setOnClickListener {
            mCallback?.loadRegistrationFragment()
        }

        view.findViewById<ImageView>(R.id.GRVControlBtn_menu).setOnClickListener {
            Toast.makeText(context, "GRV Control", Toast.LENGTH_SHORT).show()
        }

        viewModel.getUsers()

        getObservation(view = view)
    }

    private fun getObservation(view: View){

        viewModel.getUsersLiveData().observe(this){ resumeUsersList ->
            viewModel.getActiveUser(users = resumeUsersList)
        }

        viewModel.getActiveUserLiveData().observe(this){ response ->
            view.findViewById<TextView>(R.id.forenameMenu_menu).text = response.firstName
            view.findViewById<TextView>(R.id.nameMenu_menu).text = response.lastName
            view.findViewById<TextView>(R.id.emailMenu_menu).text = response.email
        }

        viewModel.getNoUserExist().observe(this){
            if (it) mCallback?.createRegistrationFragment(it)
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