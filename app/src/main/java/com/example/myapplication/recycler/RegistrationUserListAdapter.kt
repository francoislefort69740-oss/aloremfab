package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.User

class RegistrationUserListAdapter(private var userItems: List<User>, private val onItemClicked: (Int) -> Unit, private val onDeleteClick: (Int) -> Unit) : RecyclerView.Adapter<RegistrationUserListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistrationUserListHolder =
        RegistrationUserListHolder(LayoutInflater.from(parent.context), parent)

    override fun onBindViewHolder(holder: RegistrationUserListHolder, position: Int) = holder.setItem(userItems[position], onItemClicked = onItemClicked, onDeleteClick = onDeleteClick)

    override fun getItemCount(): Int = userItems.size

    fun updateData(newUsers: List<User>) {
        userItems = newUsers
        notifyDataSetChanged()
    }
}
