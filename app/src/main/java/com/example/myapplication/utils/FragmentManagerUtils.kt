package com.example.myapplication.utils

import android.util.Log
import androidx.fragment.app.FragmentManager
import com.example.myapplication.fragment.MainFragment
import com.example.myapplication.fragment.MenuFragment

fun fragmentManagerBusinessByTAG(tag: String, supportFragmentManager: FragmentManager, fragmentLayout: Int, obj: Any? = null) = when(tag) {
    MAIN_TAG -> supportFragmentManager.beginTransaction().replace(fragmentLayout, MenuFragment.newInstance(), MAIN_TAG).commit()
    /*
    CREATE_USER_TAG -> supportFragmentManager.beginTransaction().replace(fragmentLayout, CreateUserFragment.newInstance(), CREATE_USER_TAG).commit()
    AQ_SUMMARY_TAG -> supportFragmentManager.beginTransaction().replace(fragmentLayout, AQSummaryFragment.newInstance(), AQ_SUMMARY_TAG).commit()
    CREATION_AQ_TAG ->
        if (obj is Int) supportFragmentManager.beginTransaction().replace(fragmentLayout, AQCreationFragment.newInstance(obj as Int?), CREATION_AQ_TAG).commit()
        else supportFragmentManager.beginTransaction().replace(fragmentLayout, AQCreationFragment.newInstance(null), CREATION_AQ_TAG).commit()
    ML_KIT_START_TAG -> supportFragmentManager.beginTransaction().replace(fragmentLayout, MLKitStartFragment.newInstance(), ML_KIT_START_TAG).commit()
    ML_KIT_RUN_TAG ->
        if (obj is Int) supportFragmentManager.beginTransaction().replace(fragmentLayout, MLKitRunFragment.newInstance(obj as Int?), ML_KIT_RUN_TAG).commit()
        else supportFragmentManager.beginTransaction().replace(fragmentLayout, MLKitRunFragment.newInstance(null), ML_KIT_RUN_TAG).commit()
    PHOTO_TAG -> supportFragmentManager.beginTransaction().replace(fragmentLayout, CreatePhotoFragment.newInstance(), PHOTO_TAG).commit()
    NO_PHOTO_TAG -> supportFragmentManager.beginTransaction().replace(fragmentLayout, NoPhotoToTakeFragment.newInstance(), PHOTO_TAG).commit()
    VIEWPAGER_TAG ->
        if (obj is PhotoViewPagerDatas) supportFragmentManager.beginTransaction().replace(fragmentLayout, PhotoViewPagerFragment.newInstance(obj), VIEWPAGER_TAG).commit()
        else Log.i(TRIGO_FAIL, "Fragment manager error : no Fragment to load")
    TAKE_PHOTO_TAG ->
        if (obj is PhotoViewPagerDatas) supportFragmentManager.beginTransaction().replace(fragmentLayout, TakePhotoFragment.newInstance(obj), TAKE_PHOTO_TAG).commit()
        else Log.i(TRIGO_FAIL, "Fragment manager error : no Fragment to load")
    MARKER_TAG ->
        if (obj is PhotoViewPagerDatas) supportFragmentManager.beginTransaction().replace(fragmentLayout, MarkerFragment.newInstance(obj), MARKER_TAG).commit()
        else Log.i(TRIGO_FAIL, "Fragment manager error : no Fragment to load")
    PDF_TAG ->
        if (obj is Int) supportFragmentManager.beginTransaction().replace(fragmentLayout, PDFFragment.newInstance(obj as Int?), PDF_TAG).commit()
        else Log.i(TRIGO_FAIL, "Fragment manager error : no Fragment to load")
*/
    else -> Log.i(TRIGO_FAIL, "Fragment manager error : no Fragment to load")
}