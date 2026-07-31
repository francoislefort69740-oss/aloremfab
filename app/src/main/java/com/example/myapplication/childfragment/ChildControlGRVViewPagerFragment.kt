package com.example.myapplication.childfragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.utils.RETURN_TO_ADD_LIST_GRV_CONTROL
import com.example.myapplication.R
import com.example.myapplication.callback.ChildViewPagerGRVInterface
import com.example.myapplication.component.GRVControlProcess
import com.example.myapplication.component.GRVControlStepTemplate
import com.example.myapplication.fragment.BaseFragment
import com.example.myapplication.model.ControlGRV
import com.example.myapplication.model.StepControlGRV
import com.example.myapplication.recycler.StepGRVListAdapter
import com.example.myapplication.viewmodel.ControlGRVViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class ChildControlGRVViewPagerFragment: BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_child_control_grv_view_pager

    private val viewModel: ControlGRVViewModel by viewModel()

    private lateinit var controlGRVPageRecyclerView: RecyclerView
    private lateinit var mAdapterControlGRVPage: StepGRVListAdapter

    private lateinit var controlComponent: GRVControlProcess

    override fun getBody(view: View, savedInstanceState: Bundle?) {
        arguments?.let { arguments ->

            controlGRVPageRecyclerView = view.findViewById(R.id.recycler_control_area_child_control_grv)
            controlGRVPageRecyclerView.layoutManager = LinearLayoutManager(view.context)

            controlComponent =  GRVControlProcess()
            manageControlPage(view = view, arguments = arguments)

            observeLiveData()
        }
    }


    private fun manageControlPage(view: View, arguments: Bundle){
        controlComponent.setUp(view = view, arguments = arguments)

        controlComponent.closeButton().setOnClickListener {
            if (controlComponent.getControl().serialNumber != 0 && controlComponent.getControl().serialNumber != null) {
                viewModel.moveExistingControlToAddingPage(controlComponent.getControl().serialNumber ?:0, state = false)
            } else {
                mCallback?.getAddingPage()
            }
        }

        controlComponent.save().setOnClickListener {
            val result = controlComponent.translateControlStepToControlGRV(list = viewModel.getResult(), context = requireContext())
            viewModel.pushControlGRV(controlGRV = result.first, stepControlGRV = result.second)
        }

        controlComponent.next().setOnClickListener {
            viewModel.checkSaveOrNextControlGRV(controlComponent.getControl().serialNumber!!)
        }

        controlComponent.back().setOnClickListener {
            viewModel.getStepControlGrv(reference = controlComponent.getControl().serialNumber!!, stepNumber = controlComponent.decrementStep())
        }

        mAdapterControlGRVPage = StepGRVListAdapter(requireContext(),
            onItemClicked = {},
            onDeleteClick = {},
            onValueChanged = { viewModel.onCheckPointChanged() }
        )
        controlGRVPageRecyclerView.adapter = mAdapterControlGRVPage

        viewModel.getStepControlGrv(reference = controlComponent.getControl().serialNumber!!, stepNumber = controlComponent.getControl().currentStep)

    }

    // ----------------------------------------------------------------------------------------------
    // OBSERVATIONS

    private fun observeLiveData() {
        viewModel.updateControlGRVLiveData().observe(this) { trigger ->
            when (trigger) {
                RETURN_TO_ADD_LIST_GRV_CONTROL -> {
                    mCallback?.getAddingPage()
                }
            }
        }

        viewModel.updateLoadedControlGRVStateLiveData().observe(this) { controlsGRV ->
            mCallback?.getAddingPage(newList = controlsGRV.first)
        }

        viewModel.getControlGRVLiveData().observe(this) { controlGRV ->
            mCallback?.createNewPage(controlGRV.serialNumber ?: 0)
        }

        viewModel.createControlGRVLiveData().observe(this) { controlsGRV ->
            mCallback?.getAddingPage(newList = controlsGRV.first)
        }

        viewModel.getStepControlGrVLiveData().observe(this) { stepControlGRV ->
            if (::mAdapterControlGRVPage.isInitialized) {
                viewModel.loadTemplate(template = GRVControlStepTemplate(stepControlGRV, context = requireContext()))
                controlComponent.setUpBackButton(stepControlGRV::class != StepControlGRV.Step0ControlGRV::class)
            }
        }

        viewModel.createStepControlGrVLiveData().observe(this) { stepControlGRV ->
            if (::mAdapterControlGRVPage.isInitialized) {
                viewModel.getStepControlGrv(reference = controlComponent.getControl().serialNumber!!, stepNumber = controlComponent.incrementStep())
            }
        }

        viewModel.getControlGRVNotFound().observe(this) {
            if (::mAdapterControlGRVPage.isInitialized) {
                 viewModel.loadTemplate(template = GRVControlStepTemplate(controlComponent.initializeNewControl(), context = requireContext()))
            }
        }

        viewModel.wrongSerialNumber().observe(this) {
            Toast.makeText(requireContext(), getString(R.string.control_grv_control_step_wrong_report_number), Toast.LENGTH_SHORT).show()
        }

        viewModel.checkPoints.observe(viewLifecycleOwner) { list ->
            mAdapterControlGRVPage.updateData(listCheckPoint = list, context = requireContext())
        }

        viewModel.allCheckPointsCompleted.observe(viewLifecycleOwner) { completed ->
            controlComponent.setUpNextButton(completed)
        }

        viewModel.checkSaveOrNextControlGRVLiveData().observe(this){ checking ->
            if (checking.first) {
                if (checking.second) {
                    viewModel.getStepControlGrv(reference = controlComponent.getControl().serialNumber!!, stepNumber = controlComponent.incrementStep())
                } else {}
            }
        }

    }

    companion object {
        fun newInstance(controlGRV: ControlGRV) = ChildControlGRVViewPagerFragment().apply {
            arguments = Bundle().apply {
                putParcelable(GRV_CONTROL, controlGRV)
            }
        }
        const val GRV_CONTROL = "GRV_CONTROL"
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