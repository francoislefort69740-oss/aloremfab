package com.example.myapplication.recycler

import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRVCheckPoint

sealed class StepGRVListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class EditableCheckPoint(itemView: View) : StepGRVListHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.title_editable_item_card_data)
        private val input = itemView.findViewById<EditText>(R.id.input_editable_item_card_data)

        fun setItem(item: ControlGRVCheckPoint.EditableCheckPoint?, onItemClicked: (Int) -> Unit, onDeleteClick: (Int) -> Unit, onValueChanged: () -> Unit) {
            title.text = item?.title ?: ""
            input.setText(item?.name ?: "")
            input.inputType = item?.inputType ?: InputType.TYPE_CLASS_TEXT
            input.isEnabled = item?.isEnable ?: false
            input.isFocusable = item?.isEnable ?: false
            input.isFocusableInTouchMode = item?.isEnable ?: false

            input.doAfterTextChanged {
                item?.name = it.toString()
                onValueChanged()
            }
        }
    }
}