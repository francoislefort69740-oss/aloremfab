package com.example.myapplication.childfragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.callback.ChildViewPagerGRVInterface
import com.example.myapplication.component.GRVControlAddingPage
import com.example.myapplication.fragment.BaseFragment
import com.example.myapplication.recycler.ControlGRVListAdapter
import com.example.myapplication.viewmodel.ControlGRVViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class ChildAddingPageGRVViewPager: BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_adding_page_grv_view_pager

    private val viewModel: ControlGRVViewModel by viewModel()

    private lateinit var addingPageRecyclerView: RecyclerView
    private lateinit var mAdapterAddingPage: ControlGRVListAdapter

    private lateinit var addingPageComponent: GRVControlAddingPage

    private val refreshListener = {
        viewModel.getUnloadedControls()
    }

    override fun getBody(view: View, savedInstanceState: Bundle?) {

        parentFragmentManager.setFragmentResultListener("REFRESH_ADDING_PAGE", this) { _, _ ->
            viewModel.getUnloadedControls()
        }

        addingPageRecyclerView = view.findViewById(R.id.recycler_menu_list_child_control_grv)
        addingPageRecyclerView.layoutManager = LinearLayoutManager(view.context)
        addingPageComponent = GRVControlAddingPage(view = view)

        this.manageAddingPage()

        observeLiveData()
    }

    private fun manageAddingPage(){
        addingPageComponent.addingButton().setOnClickListener {
            mCallback?.createNewPage()
        }

        mAdapterAddingPage = ControlGRVListAdapter(controlsGRV = emptyList(),
            onItemClicked = { serialNumber -> viewModel.moveExistingControlToControlPage(serialNumber = serialNumber, state = true)},
            onDeleteClick = { serialNumber -> viewModel.deleteControlGRV(id = serialNumber)}
        )

        addingPageRecyclerView.adapter = mAdapterAddingPage

        viewModel.getAllCurrentlyControlGRV()
    }


    // ----------------------------------------------------------------------------------------------
    // OBSERVATIONS

    private fun observeLiveData() {
        viewModel.getCurrentlyGoingOnControlGRVLiveData().observe(this) { controlsGRV ->
            if (::mAdapterAddingPage.isInitialized) {
                mAdapterAddingPage.updateData(newLists = controlsGRV)
            }
        }

        viewModel.getPushListOfAddingPageLiveData().observe(this) { controlsGRV ->
            if (::mAdapterAddingPage.isInitialized) {
                mAdapterAddingPage.updateData(newLists = controlsGRV.first)
            }
            mCallback?.createNewPage(controlsGRV.second.serialNumber ?: 0)
        }

        viewModel.getLoadedControlGRVLiveData().observe(this) { controlsGRV ->
            if (::mAdapterAddingPage.isInitialized) {
                mAdapterAddingPage.updateData(controlsGRV)
            }
        }

        viewModel.deleteControlGRVLiveData().observe(this) { controlsGRV ->
            if (::mAdapterAddingPage.isInitialized) {
                mAdapterAddingPage.updateData(controlsGRV)
            }
        }

    }







    companion object {
        fun newInstance() = ChildAddingPageGRVViewPager()
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