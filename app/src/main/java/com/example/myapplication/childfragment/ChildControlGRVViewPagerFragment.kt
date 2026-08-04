package com.example.myapplication.childfragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.utils.GRVControlStepEnum
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
            viewModel.checkSaveOrNextControlGRV(reference = controlComponent.getControl().serialNumber!!, currentStep = controlComponent.getStepControlEnum())
        }

        controlComponent.back().setOnClickListener {
            viewModel.getStepControlGrv(reference = controlComponent.getControl().serialNumber!!, stepNumber = controlComponent.decrementStep())
        }

        controlComponent.allCheck().setOnClickListener {
            viewModel.checkAllCheckBoxes()
        }

        mAdapterControlGRVPage = StepGRVListAdapter(requireContext(),
            onItemClicked = {},
            onDeleteClick = {},
            onValueChanged = { viewModel.onCheckPointChanged() }
        )
        controlGRVPageRecyclerView.adapter = mAdapterControlGRVPage

        viewModel.getStepControlGrv(reference = controlComponent.getControl().serialNumber!!, stepNumber = controlComponent.getStepControlEnum())

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
                controlComponent.setStepControl(stepControlGRV = stepControlGRV)
                viewModel.loadTemplate(template = GRVControlStepTemplate(stepControlGRV, context = requireContext()))
                controlComponent.setUpBackButton(stepControlGRV::class != StepControlGRV.Step0ControlGRV::class)
            }
        }

        viewModel.createStepControlGrVLiveData().observe(this) { stepControlGRV ->
            if (::mAdapterControlGRVPage.isInitialized) {
                val nextStep = controlComponent.incrementStep()
                viewModel.getStepControlGrv(reference = controlComponent.getControl().serialNumber!!, stepNumber = nextStep)
            }
        }

        viewModel.getControlStepGrvNotInitialized().observe(this) { it ->
            if (::mAdapterControlGRVPage.isInitialized) {
                controlComponent.setUpBackButton(it != GRVControlStepEnum.STEP_0)
                 viewModel.loadTemplate(template = GRVControlStepTemplate(controlComponent.initializeStepControl(it), context = requireContext()))
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

        viewModel.showCheckButton.observe(viewLifecycleOwner) {
            controlComponent.setUpCheckButtons(it)
        }

        viewModel.checkPoints.observe(viewLifecycleOwner) { checkBoxes ->
            mAdapterControlGRVPage.updateData(listCheckPoint = checkBoxes, context = requireContext())
        }

        viewModel.checkSaveOrNextControlGRVLiveData().observe(this){ checking ->
            if (checking.first) {

                // THE CONTROL EXIST

                if (checking.second) {

                    // THE CURRENT STEP EXIST

                    if (checking.third) {

                        // THE NEXT STEP EXIST

                        val nextStep = controlComponent.incrementStep()
                        viewModel.getStepControlGrv(reference = controlComponent.getControl().serialNumber!!, stepNumber = nextStep)
                    } else {

                        // THE NEXT STEP DON'T EXIST : this call will update the current step of the control

                        val result = controlComponent.translateControlStepToControlGRV(list = viewModel.getResult(), context = requireContext())
                        viewModel.createStepControlGrV(step = result.second, controlGRV = result.first)

                    }
                } else {

                    // THE CURRENT STEP DON'T EXIST : this call will create the current step of the control

                    val result = controlComponent.translateControlStepToControlGRV(list = viewModel.getResult(), context = requireContext())
                    viewModel.createStepControlGrV(step = result.second, controlGRV = result.first)
                }


            } else {

                // THE CONTROL DON'T EXIST

                val result = controlComponent.translateControlStepToControlGRV(list = viewModel.getResult(), context = requireContext())
                viewModel.pushAndNextControlGRV(controlGRV = result.first, stepControlGRV = result.second)
            }
        }

        viewModel.createStepAlsoNextControlGRVLiveData().observe(this) {
            val nextStep = controlComponent.incrementStep()
            viewModel.getStepControlGrv(reference = it, stepNumber = nextStep)
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