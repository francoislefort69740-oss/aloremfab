package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.myapplication.R
import com.example.myapplication.callback.ReportControlInterface
import com.example.myapplication.utils.REPORT_TAG

class ReportFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_report

    override fun getBody(view: View, savedInstanceState: Bundle?) {
        view.findViewById<ImageView>(R.id.imageView_alorem_report_control).setOnClickListener {
            mCallback?.loadMenuFragment()
        }
    }

    companion object {
        fun newInstance() = ReportFragment()
        const val TAG = REPORT_TAG
    }

    /**
     *  LIFE CYCLE
     */

    private var mCallback: ReportControlInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try { mCallback = activity as ReportControlInterface }
        catch (e: ClassCastException) { throw ClassCastException("$e must implemented MainInterface") }
    }
}