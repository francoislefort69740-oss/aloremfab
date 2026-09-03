package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class ReportGRVExportHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(R.layout.item_grv_export, parent, false)
) {

    fun setItem(item: Pair<String, String?>, onShareClick: (Pair<String, String>) -> Unit){
        item.second?.let { reportNumber ->
            val name = itemView.findViewById<TextView>(R.id.item_grv_export_name)
            val title = item.first + reportNumber
            name.text = title

            itemView.findViewById<ImageView>(R.id.item_grv_export_send).setOnClickListener {
                onShareClick(item.first to reportNumber)
            }
        }
    }
}