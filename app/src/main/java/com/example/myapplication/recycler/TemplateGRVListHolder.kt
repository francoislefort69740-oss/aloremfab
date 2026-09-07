package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.TemplateGRV

class TemplateGRVListHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(R.layout.item_grv_template, parent, false)
){

    fun setItem(item: TemplateGRV, onEdit: (String) -> Unit) {
        val name = itemView.findViewById<TextView>(R.id.item_grv_build_name)
        name.text = item.name

        itemView.findViewById<ImageView>(R.id.item_grv_build_send).setOnClickListener {
            onEdit(item.name)
        }
    }
}