package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.findFragment
import com.example.myapplication.R
import com.example.myapplication.callback.ReportControlInterface
import com.example.myapplication.canvas.ADRReportGRV
import com.example.myapplication.utils.NUMERO
import com.example.myapplication.utils.TEMPLATE_TAG
import com.google.android.material.button.MaterialButton

class TemplateFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_template

    override fun getBody(view: View, savedInstanceState: Bundle?) {
        arguments?.getString(NAME_TEMPLATE)?.let { name ->
            val adrView = view.findViewById<ADRReportGRV>(R.id.adr_report_template)
            adrView.setNameReport(name = name, numero = NUMERO)
        }

        view.findViewById<MaterialButton>(R.id.template_back_menu).setOnClickListener {
            mCallback?.loadBuildMenu()
        }
    }

    companion object {
        fun newInstance(param1: String) =
            TemplateFragment().apply {
                arguments = Bundle().apply {
                    putString(NAME_TEMPLATE, param1)
                }
            }
        const val TAG = TEMPLATE_TAG
        const val NAME_TEMPLATE = "NAME_TEMPLATE"
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