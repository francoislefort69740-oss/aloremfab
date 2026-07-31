package com.example.myapplication.recycler

import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRVCheckPoint
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup

sealed class StepGRVListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class EditableCheckPoint(itemView: View) : StepGRVListHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.title_editable_item_card_data)
        private val input = itemView.findViewById<EditText>(R.id.input_editable_item_card_data)

        fun setItem(item: ControlGRVCheckPoint.EditableCheckPoint?, onItemClicked: (Int) -> Unit, onDeleteClick: (Int) -> Unit, onValueChanged: () -> Unit) {
            title.text = item?.name ?: ""
            input.setText(item?.value ?: "")
            input.inputType = item?.inputType ?: InputType.TYPE_CLASS_TEXT
            input.isEnabled = item?.isEnable ?: false
            input.isFocusable = item?.isEnable ?: false
            input.isFocusableInTouchMode = item?.isEnable ?: false

            input.doAfterTextChanged {
                item?.value = it.toString()
                onValueChanged()
            }
        }
    }

    class CheckBoxCheckPoint(itemView: View) : StepGRVListHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.title_checkable_item_card_data)
        private val input = itemView.findViewById<MaterialButtonToggleGroup>(R.id.toggleGroup_checkable_item_card_data)
        private val btnOk = itemView.findViewById<MaterialButton>(R.id.btnConforme_checkable_item_card_data)
        private val btnKo = itemView.findViewById<MaterialButton>(R.id.btnNonConforme_checkable_item_card_data)

        fun setItem(item: ControlGRVCheckPoint.CheckBoxCheckPoint?, onItemClicked: (Int) -> Unit, onDeleteClick: (Int) -> Unit, onValueChanged: () -> Unit) {
            title.text = item?.name ?: ""
            if (item?.isChecked == true) {
                when(item.value){
                    true -> input.check(R.id.btnConforme_checkable_item_card_data)
                    false -> input.check(R.id.btnNonConforme_checkable_item_card_data)
                    else -> input.clearChecked()
                }
            }

            btnOk.setOnClickListener {
                item?.value = true
                item?.isChecked = true
                onValueChanged()
            }

            btnKo.setOnClickListener {
                item?.value = false
                item?.isChecked = true
                onValueChanged()
            }
        }
    }
}