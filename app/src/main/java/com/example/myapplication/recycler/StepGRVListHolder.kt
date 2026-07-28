package com.example.myapplication.recycler

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRVCheckPoint

sealed class StepGRVListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class EditableCheckPoint(itemView: View) : StepGRVListHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.title_editable_item_card_data)
        private val input = itemView.findViewById<EditText>(R.id.input_editable_item_card_data)

        fun setItem(item: ControlGRVCheckPoint.EditableCheckPoint?, onItemClicked: (Int) -> Unit, onDeleteClick: (Int) -> Unit) {
            title.text = item?.title ?: ""
            input.setText(item?.name.toString())
        }
    }
}