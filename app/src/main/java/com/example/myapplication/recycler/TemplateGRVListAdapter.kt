package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.TemplateGRV

class TemplateGRVListAdapter(private var grvTemplates: List<TemplateGRV>, private val onEdit: (String) -> Unit) : RecyclerView.Adapter<TemplateGRVListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateGRVListHolder =
        TemplateGRVListHolder(inflater = LayoutInflater.from(parent.context), parent = parent)

    override fun onBindViewHolder(holder: TemplateGRVListHolder, position: Int) =
        holder.setItem(grvTemplates[position], onEdit = onEdit)

    override fun getItemCount(): Int = grvTemplates.size
}