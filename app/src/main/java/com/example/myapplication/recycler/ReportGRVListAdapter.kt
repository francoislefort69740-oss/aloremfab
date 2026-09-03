package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.ControlGRV

class ReportGRVListAdapter(private var grvItems: List<ControlGRV>,
                           private val onItemClicked: (Int) -> Unit,
                           private val onReloadClick: (Int) -> Unit,
                           private val onDeleteClick: (Int) -> Unit)
    : RecyclerView.Adapter<ReportGRVListHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportGRVListHolder =
        ReportGRVListHolder(inflater = LayoutInflater.from(parent.context), parent = parent)

    override fun onBindViewHolder(holder: ReportGRVListHolder, position: Int) =
        holder.setItem(grvItems[position], onItemClicked = onItemClicked, onDeleteClick = onDeleteClick, onReloadClick = onReloadClick)

    override fun getItemCount(): Int = grvItems.size
}