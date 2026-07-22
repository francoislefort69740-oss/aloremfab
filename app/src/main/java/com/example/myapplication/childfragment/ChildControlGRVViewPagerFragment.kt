package com.example.myapplication.childfragment

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.domain.utils.RETURN_TO_ADD_LIST_GRV_CONTROL
import com.example.myapplication.R
import com.example.myapplication.callback.ChildViewPagerGRVInterface
import com.example.myapplication.component.GRVControlProcess
import com.example.myapplication.fragment.BaseFragment
import com.example.myapplication.model.ControlGRV
import com.example.myapplication.viewmodel.ControlGRVViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class ChildControlGRVViewPagerFragment: BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_child_control_grv_view_pager

    private val viewModel: ControlGRVViewModel by viewModel()

    private lateinit var controlComponent: GRVControlProcess

    override fun getBody(view: View, savedInstanceState: Bundle?) {
        arguments?.let { arguments ->

            controlComponent =  GRVControlProcess()
            manageControlPage(view = view, arguments = arguments)

            observeLiveData()
        }
    }


    private fun manageControlPage(view: View, arguments: Bundle){
        controlComponent.setUp(view = view, arguments = arguments)


        controlComponent.closeButton().setOnClickListener {
            if (controlComponent.getControl().serialNumber != 0) {
                viewModel.moveExistingControlToAddingPage(controlComponent.getControl().serialNumber ?:0, state = false)
            } else {
                mCallback?.getAddingPage()
            }
        }

        controlComponent.save().setOnClickListener {
            viewModel.createControlGRV(ControlGRV(serialNumber = controlComponent.getFakeSerialNumber()))
        }
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