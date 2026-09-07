package com.example.myapplication.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.callback.ReportControlInterface
import com.example.myapplication.model.TemplateGRV
import com.example.myapplication.recycler.TemplateGRVListAdapter
import com.example.myapplication.utils.BUILD_TAG
import com.example.myapplication.viewmodel.TemplateViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class BuildFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_build

    private val viewModel: TemplateViewModel by viewModel()

    private lateinit var recyclerViewTemplate: RecyclerView
    private lateinit var mAdapterTemplate: TemplateGRVListAdapter

    override fun getBody(view: View, savedInstanceState: Bundle?) {
        view.findViewById<ImageView>(R.id.imageView_alorem_build_control).setOnClickListener {
            mCallback?.loadMenuFragment()
        }

        recyclerViewTemplate = view.findViewById(R.id.recycler_build)

        recyclerViewTemplate.layoutManager = LinearLayoutManager(view.context)

        observeLiveData(view = view)

        view.findViewById<ImageView>(R.id.white_build_control).setOnClickListener {
            viewModel.getAllTemplates(context = requireContext())
        }

        view.findViewById<android.widget.Button>(R.id.btn_select_folder).setOnClickListener {
            openFolderPicker()
        }

        viewModel.getAllTemplates(context = requireContext())
    }

    private val folderPickerLauncher = registerForActivityResult(
        androidx.activity.result.contract.ActivityResultContracts.OpenDocumentTree()
    ) { uri ->
        uri?.let {
            // Persist permissions for future use
            val contentResolver = requireContext().contentResolver
            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            contentResolver.takePersistableUriPermission(it, takeFlags)

            // Save URI to shared preferences
            val sharedPrefs = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            sharedPrefs.edit().putString("template_folder_uri", it.toString()).apply()

            // Refresh templates
            viewModel.getAllTemplates(context = requireContext())
        }
    }

    private fun openFolderPicker() {
        folderPickerLauncher.launch(null)
    }

    // ----------------------------------------------------------------------------------------------
    // OBSERVATIONS

    private fun observeLiveData(view: View) {
        viewModel.getAllTemplatesLiveData().observe(this) { list ->
            updateAdapter(list)
        }
    }

    private fun updateAdapter(list: List<TemplateGRV>) {
        if (::recyclerViewTemplate.isInitialized) {
            android.util.Log.d("BuildFragment", "Updating adapter with ${list.size} templates: ${list.map { it.name }}")
            mAdapterTemplate = TemplateGRVListAdapter(grvTemplates = list,
                onEdit = { serialNumber -> }
            )
            recyclerViewTemplate.adapter = mAdapterTemplate
            
            // Force visibility check
            if (list.isEmpty()) {
                android.util.Log.w("BuildFragment", "Template list is empty!")
            }
        }
    }


    companion object {
        fun newInstance() = BuildFragment()
        const val TAG = BUILD_TAG
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