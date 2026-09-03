package com.example.myapplication.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.callback.ReportControlInterface
import com.example.myapplication.canvas.ADRReportGRV
import com.example.myapplication.canvas.PeriodicReportGRV
import com.example.myapplication.model.ControlGRV
import com.example.myapplication.model.StepControlGRV
import com.example.myapplication.recycler.ReportGRVExportAdapter
import com.example.myapplication.recycler.ReportGRVListAdapter
import com.example.myapplication.utils.ADR_GRV_REPORT
import com.example.myapplication.utils.PERIODIC_GRV_REPORT
import com.example.myapplication.utils.REPORT_TAG
import com.example.myapplication.viewmodel.ReportViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class ReportFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_report

    private val viewModel: ReportViewModel by viewModel()

    private lateinit var recyclerViewCtrl: RecyclerView
    private lateinit var mAdapterList: ReportGRVListAdapter
    private lateinit var mAdapterExport: ReportGRVExportAdapter

    private lateinit var recyclerViewExport: RecyclerView

    override fun getBody(view: View, savedInstanceState: Bundle?) {
        view.findViewById<ImageView>(R.id.imageView_alorem_report_control).setOnClickListener {
            mCallback?.loadMenuFragment()
        }

        recyclerViewCtrl = view.findViewById(R.id.recycler_report)
        recyclerViewExport = view.findViewById(R.id.recycler_report_export)

        recyclerViewCtrl.layoutManager = LinearLayoutManager(view.context)
        recyclerViewExport.layoutManager = LinearLayoutManager(view.context)

        viewModel.getAllFinishedControlGRV()
        observeLiveData(view = view)

//        shareADRPdf(null)
    }

    // ----------------------------------------------------------------------------------------------
    // OBSERVATIONS

    private fun observeLiveData(view: View) {
        viewModel.getAllFinishedControlGRVLiveData().observe(this) { list ->
            updateAdapter(list)
        }

        viewModel.deleteControlGRVLiveData().observe(this) { list ->
            viewModel.getAllFinishedControlGRV()
        }

        viewModel.getControlGRVLiveData().observe(this) { controlGRV ->
            viewModel.updateControlGrc(controlGRV = controlGRV)
        }

        viewModel.updateControlGRVLiveData().observe(this) {
            viewModel.getAllFinishedControlGRV()
        }

        viewModel.getFullReportLiveData().observe(this) {
            sharePdf(it)
        }
    }

    private fun updateAdapter(list: List<ControlGRV>) {
        if (::recyclerViewCtrl.isInitialized) {
            mAdapterList = ReportGRVListAdapter(grvItems = list,
                onItemClicked = { serialNumber ->


                    // viewModel.getFullReport(id = serialNumber)
                    if (::mAdapterExport.isInitialized) mAdapterExport.updateNameReport(serialNumber.toString())


                                },
                onReloadClick = { serialNumber -> viewModel.reloadControlGRV(id = serialNumber)},
                onDeleteClick = { serialNumber -> viewModel.deleteControlGRV(id = serialNumber)}
            )
            recyclerViewCtrl.adapter = mAdapterList
        }

        if (::recyclerViewExport.isInitialized) {
            mAdapterExport = ReportGRVExportAdapter(nameReport = null,
                onShareClick = { nameReport -> when(nameReport.first) {
                    PERIODIC_GRV_REPORT -> viewModel.getFullReport(id = nameReport.second.toInt())
                    ADR_GRV_REPORT -> shareADRPdf(null)
                }}
            )
            recyclerViewExport.adapter = mAdapterExport
        }
    }

    private fun shareADRPdf(report: StepControlGRV.StepControlGRVAll?) {
        val pdfFile = File(requireContext().cacheDir, "rapport_${report?.step0?.reference ?: "unknown"}.pdf")
        val reportADRView = ADRReportGRV(requireContext())

    //    reportADRView.setDataIntoReportTemplate(reportData = report)
        reportADRView.generatePdf(pdfFile)

        val contentUri = FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}.fileprovider", pdfFile)

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, contentUri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        startActivity(Intent.createChooser(shareIntent, "Partager le rapport PDF"))
    }

    private fun sharePdf(report: StepControlGRV.StepControlGRVAll) {
        val pdfFile = File(requireContext().cacheDir, "rapport_${report.step0?.reference ?: "unknown"}.pdf")
        val reportView = PeriodicReportGRV(requireContext())

        reportView.setDataIntoReportTemplate(reportData = report)
        reportView.generatePdf(pdfFile)

        val contentUri = FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}.fileprovider", pdfFile)

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, contentUri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        startActivity(Intent.createChooser(shareIntent, "Partager le rapport PDF"))
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