package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.callback.MenuInterface
import com.example.myapplication.callback.RegistrationInterface
import com.example.myapplication.recycler.RegistrationUserListAdapter
import com.example.myapplication.utils.MENU_TAG
import com.example.myapplication.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate

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

        val grvCtrlBtn = view.findViewById<ImageView>(R.id.GRVControlBtn_menu)
        grvCtrlBtn.setOnClickListener {
            mCallback?.loadGRVControlFragment()
        }

        val reportCtrlBtn = view.findViewById<ImageView>(R.id.ReportControlBtn_menu)
        reportCtrlBtn.setOnClickListener {
            mCallback?.loadReportControlFragment()
        }

        val templateCtrlBtn = view.findViewById<ImageView>(R.id.buildControlBtn_menu)
        templateCtrlBtn.setOnClickListener {
            mCallback?.loadBuildControlFragment()
        }

        val isExpired = LocalDate.now().isAfter(LocalDate.of(2027, 4, 1))
        if (isExpired) {
            grvCtrlBtn.isEnabled = false
            reportCtrlBtn.isEnabled = false
            templateCtrlBtn.isEnabled = false
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

    private var mCallback: MenuInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try { mCallback = activity as MenuInterface }
        catch (e: ClassCastException) { throw ClassCastException("$e must implemented MainInterface") }
    }


}