package com.example.myapplication.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.utils.fragmentManagerBusinessByTAG

abstract class BaseActivity : AppCompatActivity()  {
    abstract fun getActivityId(): Int
    abstract fun getViewId(): Int
    abstract fun childBody(savedInstanceState: Bundle?)
    abstract fun getFragmentLayout(): Int
    abstract fun getFragment(): Fragment
    abstract fun getCurrentTag(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(getActivityId())
        configureFragment(bundle = savedInstanceState)
        childBody(savedInstanceState = savedInstanceState)
    }

    private fun configureFragment(bundle: Bundle?){
        if (bundle == null){
            supportFragmentManager.beginTransaction()
                .add(getFragmentLayout(), getFragment(), getCurrentTag())
                .commit()
        }
    }

    // FRAGMENT DISTRIBUTION

    protected fun buildInterface(tag: String, obj: Any? = null) {
        fragmentManagerBusinessByTAG(tag, supportFragmentManager, getFragmentLayout(), obj)
    }
}