package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.utils.ADR_GRV_REPORT
import com.example.myapplication.utils.PERIODIC_GRV_REPORT
import com.example.myapplication.utils.PHOTOS_GRV_REPORT

class ReportGRVExportAdapter(private var nameReport: String? = null, private val onShareClick: (Pair<String, String>) -> Unit) : RecyclerView.Adapter<ReportGRVExportHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportGRVExportHolder =
        ReportGRVExportHolder(inflater = LayoutInflater.from(parent.context), parent = parent)

    override fun onBindViewHolder(holder: ReportGRVExportHolder, position: Int) {
        nameReport?.let {
            if (position == 0) holder.setItem(PERIODIC_GRV_REPORT to it, onShareClick)
            if (position == 1) holder.setItem(ADR_GRV_REPORT to it, onShareClick)
            if (position == 2) holder.setItem(PHOTOS_GRV_REPORT to it, onShareClick)
        }
    }

    override fun getItemCount(): Int = if (nameReport == null) 0 else 3

    fun updateNameReport(newNameReport: String) {
        val wasEmpty = nameReport == null
        nameReport = newNameReport
        if (wasEmpty) {
            notifyItemRangeInserted(0, 3)
        } else {
            notifyItemRangeChanged(0, 3)
        }
    }

    fun clear() {
        val count = itemCount
        nameReport = null
        if (count > 0) {
            notifyItemRangeRemoved(0, count)
        }
    }
}