package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.myapplication.R
import com.example.myapplication.callback.ReportControlInterface
import com.example.myapplication.canvas.ADRReportGRV
import com.example.myapplication.canvas.FloatingDirectionNameGRV
import com.example.myapplication.canvas.FloatingNameGRVReport
import com.example.myapplication.model.TemplateGRV
import com.example.myapplication.utils.NUMERO
import com.example.myapplication.utils.TEMPLATE_TAG
import com.example.myapplication.viewmodel.TemplateViewModel
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class TemplateFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_template

    private val viewModel: TemplateViewModel by viewModel()

    override fun getBody(view: View, savedInstanceState: Bundle?) {

        val floatingName = view.findViewById<FloatingNameGRVReport>(R.id.adr_report_floating_name)

        arguments?.getString(NAME_TEMPLATE)?.let { name ->
            val adrView = view.findViewById<ADRReportGRV>(R.id.adr_report_template)
            adrView.setNameReport(name = name, numero = NUMERO)

            view.findViewById<MaterialButton>(R.id.template_back_menu).setOnClickListener {
                mCallback?.loadBuildMenu()
            }

            view.findViewById<ImageView>(R.id.template_left_arrow).setOnClickListener {
                floatingName.moveFloatingName(FloatingDirectionNameGRV.LEFT)
            }

            view.findViewById<ImageView>(R.id.template_up_arrow).setOnClickListener {
                floatingName.moveFloatingName(FloatingDirectionNameGRV.UP)
            }

            view.findViewById<ImageView>(R.id.template_arrow_down).setOnClickListener {
                floatingName.moveFloatingName(FloatingDirectionNameGRV.DOWN)
            }

            view.findViewById<ImageView>(R.id.template_right_arrow).setOnClickListener {
                floatingName.moveFloatingName(FloatingDirectionNameGRV.RIGHT)
            }

            view.findViewById<MaterialButton>(R.id.template_validate).setOnClickListener {
                val loc = floatingName.getLocalisation()
                viewModel.saveTemplate(context = requireContext(), template = TemplateGRV(name = name, x = loc.first, y = loc.second))
            }

            val scaleBtn = view.findViewById<MaterialButton>(R.id.template_ladder_menu)
            scaleBtn.setOnClickListener {
                val newScale = floatingName.changeScale()
                val newTitle = "$newScale X"
                scaleBtn.text = newTitle
            }

            viewModel.getTemplate(context = requireContext(), name = name)
        }

        observeLiveData(view = view, floatingName = floatingName)
    }

    // ----------------------------------------------------------------------------------------------
    // OBSERVATIONS

    private fun observeLiveData(view: View, floatingName: FloatingNameGRVReport) {
        viewModel.saveTemplateLiveData().observe(this) { success ->
            if (success) mCallback?.loadBuildMenu()
        }

        viewModel.getTemplateLiveData().observe(this) { templateGRV ->
            floatingName.setLocalisation(templateGRV.x, templateGRV.y)
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