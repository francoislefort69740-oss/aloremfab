package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.User

class RegistrationUserListHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(R.layout.item_user_registration, parent, false)) {

    fun setItem(item: User, clickListener: (Int) -> Unit) {
        val name = itemView.findViewById<TextView>(R.id.item_user_identity)
        name.text = item.firstName + " " + item.lastName + " - " + item.email

        itemView.findViewById<ImageView>(R.id.item_user_delete).setOnClickListener {
            clickListener(item.uid)
        }
    }
}