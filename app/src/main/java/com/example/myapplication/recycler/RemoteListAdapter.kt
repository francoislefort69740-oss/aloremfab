package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.ControlGRV

class RemoteListAdapter(private var controls: MutableList<ControlGRV>, private val onItemClicked: (Int, Int) -> Unit) : RecyclerView.Adapter<RemoteListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoteListHolder =
        RemoteListHolder(LayoutInflater.from(parent.context), parent)

    override fun onBindViewHolder(holder: RemoteListHolder, position: Int) = holder.setItem(controls[position], onItemClicked = onItemClicked)

    override fun getItemCount(): Int = controls.size

    fun removeItem(position: Int) {
        controls.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addItem(item: ControlGRV) {
        controls.add(item)
        notifyItemInserted(controls.lastIndex)
    }
}