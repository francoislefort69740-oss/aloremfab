package com.example.myapplication.childfragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Guideline
import com.example.myapplication.R
import com.example.myapplication.callback.ChildViewPagerGRVInterface
import com.example.myapplication.fragment.BaseFragment
import com.example.myapplication.model.ControlGRV
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ChildControlGRVViewPagerFragment: BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_child_control_grv_view_pager

    override fun getBody(view: View, savedInstanceState: Bundle?) {
        arguments?.let { arguments ->
            val isAddingPage = arguments.getInt(GRV_PAGE_ID) == 0
            setUpView(isAddingPage = isAddingPage, view = view)
            if (isAddingPage){ // C'est la page d'ajout de contrôle

                view.findViewById<ImageView>(R.id.add_child_control_grv).setOnClickListener {
                    mCallback?.createNewPage()
                }

            } else { // c'est une page de contrôle existante

                view.findViewById<TextView>(R.id.step_title_child_control_grv).text = arguments.getInt(GRV_PAGE_ID).toString()
                view.findViewById<ImageView>(R.id.close_child_control_grv).setOnClickListener {
                    mCallback?.deleteControl(arguments.getInt(GRV_PAGE_ID))
                }
            }
        }
    }

    private fun setUpView(isAddingPage: Boolean, view: View) {
        val mVisibility = if (isAddingPage) View.GONE else View.VISIBLE
        view.findViewById<TextView>(R.id.step_title_child_control_grv).visibility = mVisibility
        view.findViewById<FloatingActionButton>(R.id.check_all_ok_child_control_grv).visibility = mVisibility
        view.findViewById<FloatingActionButton>(R.id.save_child_control_grv).visibility = mVisibility
        view.findViewById<FloatingActionButton>(R.id.back_child_control_grv).visibility = mVisibility
        view.findViewById<FloatingActionButton>(R.id.next_child_control_grv).visibility = mVisibility
        view.findViewById<ImageView>(R.id.close_child_control_grv).visibility = mVisibility
        val mNotVisibility = if (isAddingPage) View.VISIBLE else View.GONE
        view.findViewById<ImageView>(R.id.add_child_control_grv).visibility = mNotVisibility
        val posGuideLine = if (isAddingPage) 0.68 else 0.9
        view.findViewById<Guideline>(R.id.menu_guideline_3_child_control_grv).setGuidelinePercent(posGuideLine.toFloat())
    }

    companion object {
        fun newInstance(controlGRV: ControlGRV) = ChildControlGRVViewPagerFragment().apply {
            arguments = Bundle().apply {
                controlGRV.uid?.let { putInt(GRV_ID, it)}
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