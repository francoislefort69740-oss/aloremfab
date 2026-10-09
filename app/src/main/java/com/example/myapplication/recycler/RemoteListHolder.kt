package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRV

class RemoteListHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(R.layout.item_remote, parent, false)) {

    fun setItem(item: ControlGRV, onItemClicked: (Int) -> Unit) {
        val name = itemView.findViewById<TextView>(R.id.item_id_remote)
        val nameValue = item.serialNumber.toString()
        name.text = nameValue

        this.itemView.setOnClickListener {
            onItemClicked(item.serialNumber!!)
        }
    }
}