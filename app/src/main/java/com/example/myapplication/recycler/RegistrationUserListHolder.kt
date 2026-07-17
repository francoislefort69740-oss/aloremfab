package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.User

class RegistrationUserListHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(R.layout.item_user_registration, parent, false)) {

    fun setItem(item: User, onItemClicked: (Int) -> Unit, onDeleteClick: (Int) -> Unit) {
        val name = itemView.findViewById<TextView>(R.id.item_user_identity)
        val nameValue = item.firstName + " " + item.lastName + " - " + item.email
        name.text = nameValue

        this.itemView.setOnClickListener {
            onItemClicked(item.uid)
        }

        this.itemView.isActivated = item.isActive
        this.itemView.isEnabled = !item.isActive

        itemView.findViewById<ImageView>(R.id.item_user_delete).visibility = if(item.isActive) View.GONE else View.VISIBLE
        itemView.findViewById<ImageView>(R.id.item_user_delete).setOnClickListener {
            onDeleteClick(item.uid)
        }
    }
}