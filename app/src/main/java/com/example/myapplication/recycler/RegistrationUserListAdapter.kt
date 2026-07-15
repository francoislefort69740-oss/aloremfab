package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.User

class RegistrationUserListAdapter(private val userItems: List<User>, private val clickListener: (Int) -> Unit) : RecyclerView.Adapter<RegistrationUserListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistrationUserListHolder =
        RegistrationUserListHolder(LayoutInflater.from(parent.context), parent)

    override fun onBindViewHolder(holder: RegistrationUserListHolder, position: Int) = holder.setItem(userItems[position], clickListener)

    override fun getItemCount(): Int = userItems.size
}