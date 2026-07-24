package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.ControlGRVCheckPoint

class StepGRVListAdapter(private var controlGRVCheckPoints: List<ControlGRVCheckPoint>, private val onItemClicked: (Int) -> Unit, private val onDeleteClick: (Int) -> Unit): RecyclerView.Adapter<StepGRVListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepGRVListHolder =
        StepGRVListHolder(LayoutInflater.from(parent.context), parent)

    override fun onBindViewHolder(holder: StepGRVListHolder, position: Int) = holder.setItem(controlGRVCheckPoints[position], onItemClicked = onItemClicked, onDeleteClick = onDeleteClick)

    override fun getItemCount(): Int = controlGRVCheckPoints.size
}