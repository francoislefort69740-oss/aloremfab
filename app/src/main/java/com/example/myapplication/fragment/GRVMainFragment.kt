package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.callback.GRVControlInterface
import com.example.myapplication.childfragment.ControlGRVViewPagerAdapter
import com.example.myapplication.utils.GRV_CONTROL_TAG
import com.example.myapplication.utils.MENU_TAG
import com.example.myapplication.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class GRVMainFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_grv_main

    private val viewModel: MainViewModel by viewModel()

    private lateinit var controlGRVViewPager: ViewPager2
    private lateinit var controlGRVViewPagerAdapter: ControlGRVViewPagerAdapter

    companion object {
        fun newInstance() = GRVMainFragment()
        const val TAG = GRV_CONTROL_TAG
    }

    override fun getBody(view: View, savedInstanceState: Bundle?) {
        view.findViewById<ImageView>(R.id.imageView_alorem_grv_control).setOnClickListener {
            mCallback?.loadMenuFragment()
        }
        controlGRVViewPagerAdapter = ControlGRVViewPagerAdapter(this, 0)
        controlGRVViewPager = view.findViewById(R.id.viewPager_grv_control)
        controlGRVViewPager.adapter = controlGRVViewPagerAdapter
    }

    /**
     *  LIFE CYCLE
     */

    private var mCallback: GRVControlInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try { mCallback = activity as GRVControlInterface }
        catch (e: ClassCastException) { throw ClassCastException("$e must implemented MainInterface") }
    }
}