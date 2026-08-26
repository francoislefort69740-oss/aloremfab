package com.example.myapplication.recycler

import android.view.View
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRVCheckPoint
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup

class FourStateCheckPointHolder(itemView: View) : StepGRVListHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.title_four_state_item_card_data)
    private val input = itemView.findViewById<MaterialButtonToggleGroup>(R.id.toggleGroup_four_state_item_card_data)
    private val input2 = itemView.findViewById<MaterialButtonToggleGroup>(R.id.toggleGroup2_four_state_item_card_data)
    private val btnOK = input.findViewById<MaterialButton>(R.id.btnConforme_four_state_item_card_data)
    private val btnKO = input.findViewById<MaterialButton>(R.id.btnNonConforme_four_state_item_card_data)
    private val btnAnom = input2.findViewById<MaterialButton>(R.id.btn_anom_four_state_item_card_data)
    private val btnMinor = input2.findViewById<MaterialButton>(R.id.btn_minor_four_state_item_card_data)
    private val btnMajor = input2.findViewById<MaterialButton>(R.id.btn_major_four_state_item_card_data)

    private var currentItem: ControlGRVCheckPoint.FourStateCheckPoint? = null
    private var onValueChangedCallback: (() -> Unit)? = null

    init {
        btnOK.setOnClickListener {
            currentItem?.apply {
                value = 1
                isChecked = true
            }
            input2.visibility = View.GONE
            onValueChangedCallback?.invoke()
        }

        btnKO.setOnClickListener {
            currentItem?.apply {
                value = 2
                isChecked = true
            }
            input2.visibility = View.VISIBLE
            input2.check(R.id.btn_anom_four_state_item_card_data)
            onValueChangedCallback?.invoke()
        }

        btnAnom.setOnClickListener {
            currentItem?.apply {
                value = 2
                isChecked = true
            }
            onValueChangedCallback?.invoke()
        }

        btnMinor.setOnClickListener {
            currentItem?.apply {
                value = 3
                isChecked = true
            }
            onValueChangedCallback?.invoke()
        }

        btnMajor.setOnClickListener {
            currentItem?.apply {
                value = 4
                isChecked = true
            }
            onValueChangedCallback?.invoke()
        }

        input2.visibility = View.GONE

    }

    fun setItem(item: ControlGRVCheckPoint.FourStateCheckPoint?, onItemClicked: (Int) -> Unit, onDeleteClick: (Int) -> Unit, onValueChanged: () -> Unit) {
        currentItem = item
        this.onValueChangedCallback = onValueChanged
        title.text = item?.name ?: ""

        input.clearChecked()
        input2.clearChecked()

        input2.visibility = View.GONE

        if (item?.isChecked != true) {
            return
        }
        when (item.value) {

            1 -> {
                input.check(R.id.btnConforme_four_state_item_card_data)
            }

            2 -> {
                input.check(R.id.btnNonConforme_four_state_item_card_data)
                input2.visibility = View.VISIBLE
                input2.check(R.id.btn_anom_four_state_item_card_data)
            }

            3 -> {
                input.check(R.id.btnNonConforme_four_state_item_card_data)
                input2.visibility = View.VISIBLE
                input2.check(R.id.btn_minor_four_state_item_card_data)
            }

            4 -> {
                input.check(R.id.btnNonConforme_four_state_item_card_data)
                input2.visibility = View.VISIBLE
                input2.check(R.id.btn_major_four_state_item_card_data)
            }

        }

    }




}