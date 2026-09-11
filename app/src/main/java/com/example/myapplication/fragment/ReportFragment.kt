package com.example.myapplication.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
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

        viewModel.getFullReportForPeriodicLiveData().observe(this) {
            sharePeriodicPdf(it)
        }

        viewModel.getFullReportForADRLiveData().observe(this) {
            shareADRPdf(it)
        }
    }

    private fun updateAdapter(list: List<ControlGRV>) {
        if (::recyclerViewCtrl.isInitialized) {
            mAdapterList = ReportGRVListAdapter(grvItems = list,
                onItemClicked = { serialNumber -> if (::mAdapterExport.isInitialized) mAdapterExport.updateNameReport(serialNumber.toString()) },
                onReloadClick = { serialNumber -> viewModel.reloadControlGRV(id = serialNumber)},
                onDeleteClick = { serialNumber -> viewModel.deleteControlGRV(id = serialNumber)}
            )
            recyclerViewCtrl.adapter = mAdapterList
        }

        if (::recyclerViewExport.isInitialized) {
            mAdapterExport = ReportGRVExportAdapter(nameReport = null,
                onShareClick = { nameReport -> viewModel.getFullReport(id = nameReport.second.toInt(), type = nameReport.first, context = requireContext()) }
            )
            recyclerViewExport.adapter = mAdapterExport
        }
    }

    private fun shareADRPdf(report: StepControlGRV.StepControlGRVAll?) {
        val serialNumberAlorem = "ADR_${report?.step0?.type ?: "unknown"}_${report?.step0?.serialNumberAlorem ?: "unknown"}"
        val pdfFile = File(requireContext().cacheDir, "$serialNumberAlorem.pdf")
        val reportADRView = ADRReportGRV(requireContext())

        val type = report?.step0?.type ?: ""
        val capacity = report?.step2?.capacity20 ?: 0
        val tare = report?.step2?.tare ?: 0
        val reportName = "${type}_${capacity}_${tare}"
        val foundReportName = reportADRView.setNameReport(name = reportName, numero = report?.step0?.serialNumberAlorem.toString(), x = report?.floatX ?: 0F, y = report?.floatY ?: 0F)
        
        if (foundReportName != null) {
            reportADRView.generatePdf(pdfFile)

            if (pdfFile.exists() && pdfFile.length() > 0) {
                val contentUri = FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}.fileprovider", pdfFile)
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    this.type = "application/pdf"
                    putExtra(Intent.EXTRA_STREAM, contentUri)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                startActivity(Intent.createChooser(shareIntent, "Partager le rapport PDF"))
            } else {
                Toast.makeText(context, "Erreur lors de la génération du fichier PDF", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Erreur: Template non reconnu ($reportName). Vérifiez vos fichiers PDF.", Toast.LENGTH_LONG).show()
        }
    }

    private fun sharePeriodicPdf(report: StepControlGRV.StepControlGRVAll) {
        val pdfFile = File(requireContext().cacheDir,
            "RAPPORT_${report.step0?.reportNumber}_${report.step0?.type}_${report.step0?.serialNumberAlorem}.pdf")
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