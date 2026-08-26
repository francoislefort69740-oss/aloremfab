package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.ControlGRV

class ControlGRVListAdapter(private var controlsGRV: List<ControlGRV>, private val onItemClicked: (Int) -> Unit, private val onDeleteClick: (Int) -> Unit): RecyclerView.Adapter<ControlGRVListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ControlGRVListHolder =
        ControlGRVListHolder(LayoutInflater.from(parent.context), parent)

    override fun onBindViewHolder(holder: ControlGRVListHolder, position: Int) = holder.setItem(controlsGRV[position], onItemClicked = onItemClicked, onDeleteClick = onDeleteClick)

    override fun getItemCount(): Int = controlsGRV.size

    fun updateData(newLists: List<ControlGRV>) {
        controlsGRV = newLists
        notifyDataSetChanged()
    }
}