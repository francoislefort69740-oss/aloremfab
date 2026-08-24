package com.example.myapplication.recycler

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRVCheckPoint

class SingleCheckCheckPointHolder(itemView: View) : StepGRVListHolder(itemView) {
    private val title = itemView.findViewById<TextView>(R.id.title_single_check_item_card_data)
    private val input = itemView.findViewById<CheckBox>(R.id.checkbox_single_check_item_card_data)

    private var currentItem: ControlGRVCheckPoint.SingleCheckCheckPoint? = null
    private var onValueChangedCallback: (() -> Unit)? = null

    init {
        input.setOnClickListener { currentItem?.apply {
            value = input.isChecked
        }
            onValueChangedCallback?.invoke()
        }
    }

    fun setItem(item: ControlGRVCheckPoint.SingleCheckCheckPoint?, onItemClicked: (Int) -> Unit, onDeleteClick: (Int) -> Unit, onValueChanged: () -> Unit) {
        currentItem = item
        this.onValueChangedCallback = onValueChanged
        title.text = item?.name ?: ""
        input.isChecked = item?.value ?: false
        input.isEnabled = item?.isEnable ?: true
    }
}