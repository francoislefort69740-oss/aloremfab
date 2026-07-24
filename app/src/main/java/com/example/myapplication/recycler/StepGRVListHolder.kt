package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRV
import com.example.myapplication.model.ControlGRVCheckPoint

class StepGRVListHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(R.layout.item_grv_data_card, parent, false)) {

    fun setItem(item: ControlGRVCheckPoint, onItemClicked: (Int) -> Unit, onDeleteClick: (Int) -> Unit) {
        val name = itemView.findViewById<TextView>(R.id.report_number_item_card_data)
        name.text = item.title

    }
}