package com.example.myapplication.recycler

import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRVCheckPoint

class EditableCheckPointHolder(itemView: View) : StepGRVListHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.title_editable_item_card_data)
    private val input = itemView.findViewById<EditText>(R.id.input_editable_item_card_data)

    private var currentItem: ControlGRVCheckPoint.EditableCheckPoint? = null
    private var onValueChangedCallback: (() -> Unit)? = null
    private var binding = false

    init {
        input.doAfterTextChanged { if (!binding) {
            currentItem?.let { item ->
                item.value = it.toString()
                onValueChangedCallback?.invoke()
            }
        }}
    }

    fun setItem(item: ControlGRVCheckPoint.EditableCheckPoint?, onItemClicked: (Int) -> Unit, onDeleteClick: (Int) -> Unit, onValueChanged: () -> Unit) {
        currentItem = item
        onValueChangedCallback = onValueChanged
        binding = true
        title.text = item?.name ?: ""
        if (input.text.toString() != item?.value) input.setText(item?.value ?: "")
        input.inputType = item?.inputType ?: InputType.TYPE_CLASS_TEXT
        input.isEnabled = item?.isEnable ?: false
        input.isFocusable = item?.isEnable ?: false
        input.isFocusableInTouchMode = item?.isEnable ?: false
        binding = false
    }
}