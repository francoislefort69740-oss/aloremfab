package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.callback.RegistrationInterface
import com.example.myapplication.model.User
import com.example.myapplication.recycler.RegistrationUserListAdapter
import com.example.myapplication.utils.REGISTRATION_TAG
import com.example.myapplication.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue


class RegistrationFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_registration

    private val viewModel: MainViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: RegistrationUserListAdapter

    companion object {
        fun newInstance() = RegistrationFragment()
        const val TAG = REGISTRATION_TAG
    }

    override fun getBody(view: View, savedInstanceState: Bundle?) {
        view.findViewById<FloatingActionButton>(R.id.registration_exit).setOnClickListener {
            mCallback?.loadMenuFragment()
        }

        view.findViewById<Button>(R.id.newUser_registration).setOnClickListener {
            mCallback?.createRegistrationFragment(false)
        }

        recyclerView = view.findViewById(R.id.recycler_registration)
        viewModel.getUsers()
        getObservations(view= view)
    }

    private fun getObservations(view: View){
        viewModel.getUsersLiveData().observe(this){ resumeUsersList ->
            mAdapter = RegistrationUserListAdapter(resumeUsersList,
                onItemClicked = { userId -> viewModel.updateId(activeId = userId, users = resumeUsersList)},
                onDeleteClick = { userId -> viewModel.deleteUser(uid = userId, users = resumeUsersList)}
            )

            recyclerView.adapter = mAdapter

            viewModel.getActiveUser(users = resumeUsersList)
        }

        viewModel.getActiveUserLiveData().observe(this){ user ->
            view.findViewById<TextView>(R.id.nameMenu_registration).text = user.lastName
            view.findViewById<TextView>(R.id.forenameMenu_registration).text = user.firstName
            view.findViewById<TextView>(R.id.emailMenu_menu).text = user.email
            if (::mAdapter.isInitialized) {
                mAdapter.notifyDataSetChanged()
            }
        }

        viewModel.updateActiveIdLiveData().observe(this){ resumeUsersList ->
            if (::mAdapter.isInitialized) {
                mAdapter.updateData(resumeUsersList)
            }
            viewModel.getActiveUser(users = resumeUsersList)
        }

        viewModel.deleteUserLiveData().observe(this){ resumeUsersList ->
            if (::mAdapter.isInitialized) {
                mAdapter.updateData(resumeUsersList)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(view.context)
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