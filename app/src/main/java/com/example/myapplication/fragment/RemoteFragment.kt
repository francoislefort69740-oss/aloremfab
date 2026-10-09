package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.callback.GRVControlInterface
import com.example.myapplication.utils.REMOTE_TAG
import com.example.myapplication.viewmodel.ControlGRVViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class RemoteFragment : BaseFragment() {

    override fun getLayout(): Int = R.layout.fragment_remote

    private val viewModel: ControlGRVViewModel by viewModel()

    override fun getBody(view: View, savedInstanceState: Bundle?) {
        view.findViewById<ImageView>(R.id.imageView_alorem_remote_grv).setOnClickListener {
            mCallback?.loadMenuFragment()
        }

        viewModel.getAllRemoteAndLocalControlGRV(baseUrl = "http://10.0.121.61:8080/")
        getObservations(view= view)
    }

    private fun getObservations(view: View) {
        viewModel.remoteControlGRVLiveData().observe(this) { lists ->

            var firstList = ""
            var secondList = ""

            lists.first.forEach {
                firstList += it.serialNumber.toString() + "\n"
            }
            lists.second.forEach {
                secondList += it.serialNumber.toString() + "\n"
            }

            val tempText = firstList + "\n\n" + secondList
            view.findViewById<TextView>(R.id.temp_remote_grv).text = tempText
        }
    }


    companion object {
        fun newInstance() = RemoteFragment()
        const val TAG = REMOTE_TAG
    }

    /**
     *  LIFE CYCLE
     */

    private var mCallback: GRVControlInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try { mCallback = activity as GRVControlInterface }
        catch (e: ClassCastException) { throw ClassCastException("$e must implemented MainInterface") }
    }
}