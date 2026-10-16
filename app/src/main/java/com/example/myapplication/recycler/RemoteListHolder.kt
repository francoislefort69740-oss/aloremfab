package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRV

class RemoteListHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(R.layout.item_remote, parent, false)) {

    fun setItem(item: ControlGRV, onItemClicked: (Int, Int) -> Unit) {
        val name = itemView.findViewById<TextView>(R.id.item_id_remote)
        name.text = item.serialNumber.toString()

        itemView.setOnClickListener {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onItemClicked(position, item.serialNumber!!)
            }
        }
    }
}