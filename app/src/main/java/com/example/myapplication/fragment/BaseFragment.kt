package com.example.myapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.utils.CHECK_GRV
import com.example.myapplication.utils.PERF_GRV

abstract class BaseFragment : Fragment() {

    private var startTime = 0L
    private var startRender = 0L

    // UI : Definition abstract methods
    abstract fun getLayout(): Int
    abstract fun getBody(view: View, savedInstanceState: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        startTime = System.currentTimeMillis()
        Log.d(PERF_GRV, "onCreate")
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bodyStart = System.currentTimeMillis()
        val startRender = System.currentTimeMillis()
        Log.d(PERF_GRV, "onViewCreated : ${System.currentTimeMillis() - startTime} ms")
        getBody(view, savedInstanceState)
        Log.d(PERF_GRV, "getBody terminé : ${System.currentTimeMillis() - bodyStart} ms")
        view.post { Log.d(PERF_GRV, "Premier affichage complet : ${System.currentTimeMillis() - startRender} ms") }
    }
}