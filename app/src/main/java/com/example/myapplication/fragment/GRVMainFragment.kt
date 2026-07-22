package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.callback.ChildViewPagerGRVInterface
import com.example.myapplication.callback.GRVControlInterface
import com.example.myapplication.childfragment.ControlGRVViewPagerAdapter
import com.example.myapplication.model.ControlGRV
import com.example.myapplication.utils.GRV_CONTROL_TAG
import com.example.myapplication.utils.ZoomOutPageTransformer

class GRVMainFragment : BaseFragment(), ChildViewPagerGRVInterface {
    override fun getLayout(): Int = R.layout.fragment_grv_main

    private lateinit var controlGRVViewPager: ViewPager2
    private lateinit var controlGRVViewPagerAdapter: ControlGRVViewPagerAdapter
    private val controls = mutableListOf<ControlGRV>()

    companion object {
        fun newInstance() = GRVMainFragment()
        const val TAG = GRV_CONTROL_TAG
    }

    override fun getBody(view: View, savedInstanceState: Bundle?) {
        view.findViewById<ImageView>(R.id.imageView_alorem_grv_control).setOnClickListener {
            mCallback?.loadMenuFragment()
        }
        controlGRVViewPagerAdapter = ControlGRVViewPagerAdapter(this, controls)
        controlGRVViewPager = view.findViewById(R.id.viewPager_grv_control)
        controlGRVViewPager.setPageTransformer(ZoomOutPageTransformer())
        controlGRVViewPager.adapter = controlGRVViewPagerAdapter
    }

    override fun createNewPage(serialNumber: Int) {
        val newPosition = controls.size
        controls.add(ControlGRV(pageId = System.nanoTime().toInt(), serialNumber = serialNumber))
        controlGRVViewPagerAdapter.notifyItemInserted(newPosition)
        
        // Scroll to the newly created page (which is the penultimate page, just before the Adding Page)
        controlGRVViewPager.post {
            controlGRVViewPager.setCurrentItem(newPosition, true)
        }
    }

    override fun getAddingPage(newList: List<ControlGRV>?) {
        val pos = controlGRVViewPager.currentItem
        // Check if we are trying to remove a valid control (not the Adding Page itself)
        if (pos < controls.size) {
            controls.removeAt(pos)
            controlGRVViewPagerAdapter.notifyItemRemoved(pos)
            
            // Return to the Adding Page, which is always at the last index (itemCount - 1)
            controlGRVViewPager.post {
                controlGRVViewPager.setCurrentItem(controlGRVViewPagerAdapter.itemCount - 1, true)
                newList?.let {
                    childFragmentManager.setFragmentResult("REFRESH_ADDING_PAGE", Bundle())
                }
            }
        }
    }

    override fun saveControl() {
        mCallback?.loadMenuFragment()
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