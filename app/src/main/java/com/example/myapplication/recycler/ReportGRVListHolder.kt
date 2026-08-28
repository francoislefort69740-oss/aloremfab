package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRV

class ReportGRVListHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(R.layout.item_grv_report, parent, false)
) {

    fun setItem(item: ControlGRV, onItemClicked: (Int) -> Unit, onDeleteClick: (Int) -> Unit, onReloadClick: (Int) -> Unit){
        val name = itemView.findViewById<TextView>(R.id.item_grv_report_name)
        name.text = item.serialNumber.toString()

        itemView.setOnClickListener {
            item.serialNumber?.let { onItemClicked(it) }
        }

        itemView.findViewById<ImageView>(R.id.item_report_delete).setOnClickListener {
            item.serialNumber?.let { onDeleteClick(it) }
        }

        itemView.findViewById<ImageView>(R.id.item_report_reload).setOnClickListener {
            item.serialNumber?.let { onReloadClick(it) }
        }
    }

}