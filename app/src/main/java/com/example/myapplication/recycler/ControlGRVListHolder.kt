package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRV

class ControlGRVListHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(R.layout.item_control_grv_card, parent, false)) {

    fun setItem(item: ControlGRV, onItemClicked: (Int) -> Unit, onDeleteClick: (Int) -> Unit) {
        val name = itemView.findViewById<TextView>(R.id.item_control_grv_card_number)
        val nameValue = item.uid.toString()
        name.text = nameValue

        itemView.findViewById<ImageView>(R.id.item_control_grv_card_delete).setOnClickListener {
            onDeleteClick(item.uid!!)
        }

        itemView.findViewById<ImageView>(R.id.item_control_grv_card_inject).setOnClickListener {
            onItemClicked(item.uid!!)
        }
    }
}