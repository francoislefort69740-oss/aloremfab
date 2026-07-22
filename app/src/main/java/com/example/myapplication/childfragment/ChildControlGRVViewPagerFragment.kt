package com.example.myapplication.childfragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.callback.ChildViewPagerGRVInterface
import com.example.myapplication.component.GRVControlAddingPage
import com.example.myapplication.component.GRVControlProcess
import com.example.myapplication.fragment.BaseFragment
import com.example.myapplication.model.ControlGRV
import com.example.myapplication.recycler.ControlGRVListAdapter
import com.example.myapplication.viewmodel.ControlGRVViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class ChildControlGRVViewPagerFragment: BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_child_control_grv_view_pager

    private val viewModel: ControlGRVViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: ControlGRVListAdapter

    private val controlComponent: GRVControlProcess = GRVControlProcess()
    private val addingPageComponent: GRVControlAddingPage = GRVControlAddingPage()

    override fun getBody(view: View, savedInstanceState: Bundle?) {
        arguments?.let { arguments ->

            recyclerView = view.findViewById(R.id.recycler_child_control_grv)
            recyclerView.layoutManager = LinearLayoutManager(view.context)

            if (arguments.getInt(GRV_PAGE_ID) == 0){ // C'est la page d'ajout de contrôle

                addingPageComponent.setUp(view = view, arguments = arguments)

                addingPageComponent.addingButton().setOnClickListener {
                    mCallback?.createNewPage()
                }

                viewModel.getAllControlGRV()

            } else { // c'est une page de contrôle existante

                controlComponent.setUp(view = view, arguments = arguments)

                controlComponent.closeButton().setOnClickListener {
                    mCallback?.deleteControl(pos = controlComponent.getPageId())
                }

                controlComponent.save().setOnClickListener {
                    viewModel.createControlGRV(ControlGRV(serialNumber = controlComponent.getFakeSerialNumber()))
                }
            }
            observeLiveData()
        }
    }


    // OBSERVATIONS

    private fun observeLiveData() {
        viewModel.getAllControlGRVLiveData().observe(this) { controlsGRV ->
            mAdapter = ControlGRVListAdapter(controlsGRV = controlsGRV,
                onItemClicked = { serialNumber -> },
                onDeleteClick = { serialNumber -> viewModel.deleteControlGRV(id = serialNumber)}
            )

            recyclerView.adapter = mAdapter
            mAdapter.notifyDataSetChanged()
        }

        viewModel.createControlGRVLiveData().observe(this) { controlGRVS ->
            Toast.makeText(context, "CONTROL SAVED", Toast.LENGTH_SHORT).show()
        }

        viewModel.deleteControlGRVLiveData().observe(this) { controlsGRV ->
            if (::mAdapter.isInitialized) {
                mAdapter.updateData(controlsGRV)
            }
        }
    }

    companion object {
        fun newInstance(controlGRV: ControlGRV) = ChildControlGRVViewPagerFragment().apply {
            arguments = Bundle().apply {
                putInt(GRV_ID, controlGRV.uid ?: 0)
                putInt(GRV_PAGE_ID, controlGRV.pageId)
                controlGRV.currentStep?.id?.let { putInt(GRV_CURRENT_STEP, it) }
            }
        }
        const val GRV_ID = "GRV_ID"
        const val GRV_PAGE_ID = "GRV_PAGE_ID"
        const val GRV_CURRENT_STEP = "GRV_CURRENT_STEP"
    }

    /**
     *  LIFE CYCLE
     */

    private var mCallback: ChildViewPagerGRVInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try { mCallback = parentFragment as ChildViewPagerGRVInterface }
        catch (e: ClassCastException) { throw ClassCastException("$e must implemented MainInterface") }
    }
}