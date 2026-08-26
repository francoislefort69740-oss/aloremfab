package com.example.myapplication.recycler

import android.view.View
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRVCheckPoint
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup

class CheckBoxCheckPointHolder(itemView: View) : StepGRVListHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.title_checkable_item_card_data)
    private val input = itemView.findViewById<MaterialButtonToggleGroup>(R.id.toggleGroup_checkable_item_card_data)
    private val btnOk = itemView.findViewById<MaterialButton>(R.id.btnConforme_checkable_item_card_data)
    private val btnKo = itemView.findViewById<MaterialButton>(R.id.btnNonConforme_checkable_item_card_data)

    private var currentItem: ControlGRVCheckPoint.CheckBoxCheckPoint? = null
    private var onValueChangedCallback: (() -> Unit)? = null

    init {
        btnOk.setOnClickListener { currentItem?.apply {
            value = true
            isChecked = true
        }
            onValueChangedCallback?.invoke()
        }

        btnKo.setOnClickListener { currentItem?.apply {
            value = false
            isChecked = true
        }
            onValueChangedCallback?.invoke()
        }
    }

    fun setItem(item: ControlGRVCheckPoint.CheckBoxCheckPoint?, onItemClicked: (Int) -> Unit, onDeleteClick: (Int) -> Unit, onValueChanged: () -> Unit) {
        currentItem = item
        onValueChangedCallback = onValueChanged
        title.text = item?.name ?: ""
        when (item?.value) {
            true -> {
                input.check(R.id.btnConforme_checkable_item_card_data)
            }

            false -> {
                input.check(R.id.btnNonConforme_checkable_item_card_data)
            }

            null -> {
                input.clearChecked()
            }
        }
    }
}