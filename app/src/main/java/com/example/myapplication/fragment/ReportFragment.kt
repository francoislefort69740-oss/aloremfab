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
import android.provider.MediaStore
import com.example.myapplication.canvas.ADRReportGRV
import com.example.myapplication.canvas.PeriodicReportGRV
import com.example.myapplication.model.ControlGRV
import com.example.myapplication.model.StepControlGRV
import com.example.myapplication.recycler.ReportGRVExportAdapter
import com.example.myapplication.recycler.ReportGRVListAdapter
import com.example.myapplication.utils.ADVERTISING_NO_PHOTO_FOUND
import com.example.myapplication.utils.JPEG_TYPE
import com.example.myapplication.utils.PDF_CREATION_FAILED
import com.example.myapplication.utils.PDF_TYPE
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

        viewModel.deleteControlGRVLiveData().observe(this) { name ->
            deletePhotosByName(name)
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

        viewModel.checkIfTemplateGRVExistLiveData().observe(this) {
            if (::mAdapterExport.isInitialized) mAdapterExport.updateNameReport(newNameReport = it)
        }

        viewModel.getPhotoReportLiveData().observe(this) { name ->
            sharePhotosByName(name)
        }

        viewModel.noTemplateGRVExistLiveData().observe(this) {
            Toast.makeText(context, "Aucun rapport ADR à exporter", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateAdapter(list: List<ControlGRV>) {
        if (::recyclerViewCtrl.isInitialized) {
            mAdapterList = ReportGRVListAdapter(grvItems = list,
                onItemClicked = { serialNumber ->
                    mAdapterExport.clear()
                    viewModel.checkIfTemplateGRVExist(name = serialNumber.toString(), context = requireContext()) },
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
                    this.type = PDF_TYPE
                    putExtra(Intent.EXTRA_STREAM, contentUri)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                startActivity(Intent.createChooser(shareIntent, "Partager le rapport PDF"))
            } else {
                Toast.makeText(context, PDF_CREATION_FAILED, Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Erreur: Template non reconnu ($reportName). Vérifiez vos fichiers PDF.", Toast.LENGTH_LONG).show()
        }
    }

    private fun deletePhotosByName(name: String) {
        val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val selection = "${MediaStore.Images.Media.DISPLAY_NAME} LIKE ? AND ${MediaStore.Images.Media.RELATIVE_PATH} LIKE ?"
        val selectionArgs = arrayOf("$name%", "%Pictures/Alorem%")

        try {
            requireContext().contentResolver.delete(collection, selection, selectionArgs)
        } catch (e: Exception) {
            android.util.Log.e("ReportFragment", "Error deleting photos for $name", e)
        }
    }

    private fun sharePhotosByName(name: String) {
        val imageUris = ArrayList<android.net.Uri>()
        val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME)
        val selection = "${MediaStore.Images.Media.DISPLAY_NAME} LIKE ? AND ${MediaStore.Images.Media.RELATIVE_PATH} LIKE ?"
        val selectionArgs = arrayOf("$name%", "%Pictures/Alorem%")

        requireContext().contentResolver.query(collection, projection, selection, selectionArgs, null)?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val uri = android.content.ContentUris.withAppendedId(collection, id)
                imageUris.add(uri)
            }
        }

        if (imageUris.isNotEmpty()) {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND_MULTIPLE
                putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris)
                type = JPEG_TYPE
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            startActivity(Intent.createChooser(intent, "Partager les photos de $name"))
        } else {
            Toast.makeText(requireContext(), "$ADVERTISING_NO_PHOTO_FOUND $name", Toast.LENGTH_SHORT).show()
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
            type = PDF_TYPE
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