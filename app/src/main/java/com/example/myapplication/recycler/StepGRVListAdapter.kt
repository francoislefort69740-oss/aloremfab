package com.example.myapplication.recycler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.R
import com.example.myapplication.component.GRVControlStepTemplate
import com.example.myapplication.model.ControlGRVCheckPoint
import com.example.myapplication.model.StepControlGRV
import com.example.myapplication.utils.PERF_GRV

class StepGRVListAdapter(
    private val context: Context,
    private val onItemClicked: (Int) -> Unit,
    private val onDeleteClick: (Int) -> Unit,
    private val onValueChanged: () -> Unit
) : ListAdapter<ControlGRVCheckPoint, StepGRVListHolder>(DiffCallback()) {


    private var mTemplate = GRVControlStepTemplate(context = context)

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ControlGRVCheckPoint.EditableCheckPoint ->
                VIEW_TYPE_EDITABLE
            is ControlGRVCheckPoint.CheckBoxCheckPoint ->
                VIEW_TYPE_CHECKBOX
            is ControlGRVCheckPoint.FourStateCheckPoint ->
                VIEW_TYPE_FOUR_STATE
            is ControlGRVCheckPoint.SingleCheckCheckPoint ->
                VIEW_TYPE_SINGLE_STATE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepGRVListHolder {
        val inflater = LayoutInflater.from(parent.context)

        Log.d(PERF_GRV, "onCreateViewHolder type=$viewType")

        return when (viewType) {
            VIEW_TYPE_EDITABLE -> {
                EditableCheckPointHolder(inflater.inflate(R.layout.item_grv_data_card_editable, parent, false))
            }
            VIEW_TYPE_CHECKBOX -> {
                CheckBoxCheckPointHolder(inflater.inflate(R.layout.item_grv_data_card_checkable, parent, false))
            }
            VIEW_TYPE_FOUR_STATE -> {
                FourStateCheckPointHolder(inflater.inflate(R.layout.item_grv_data_card_four_state, parent, false))
            }
            VIEW_TYPE_SINGLE_STATE -> {
                SingleCheckCheckPointHolder(inflater.inflate(R.layout.item_grv_data_card_single_state, parent, false))
            }
            else -> throw IllegalArgumentException("Unknown viewType : $viewType")
        }
    }

    override fun onBindViewHolder(holder: StepGRVListHolder, position: Int) {

        val start = System.currentTimeMillis()
        Log.d(PERF_GRV, "START bind position=$position")

        when (val item = getItem(position)) {
            is ControlGRVCheckPoint.EditableCheckPoint -> {
                (holder as EditableCheckPointHolder).setItem(
                        item = item,
                        onItemClicked = onItemClicked,
                        onDeleteClick = onDeleteClick,
                        onValueChanged = onValueChanged
                )
            }
            is ControlGRVCheckPoint.CheckBoxCheckPoint -> {
                (holder as CheckBoxCheckPointHolder).setItem(
                        item = item,
                        onItemClicked = onItemClicked,
                        onDeleteClick = onDeleteClick,
                        onValueChanged = onValueChanged
                )
            }
            is ControlGRVCheckPoint.FourStateCheckPoint -> {
                (holder as FourStateCheckPointHolder).setItem(
                    item = item,
                    onItemClicked = onItemClicked,
                    onDeleteClick = onDeleteClick,
                    onValueChanged = onValueChanged
                )
            }
            is ControlGRVCheckPoint.SingleCheckCheckPoint -> {
                (holder as SingleCheckCheckPointHolder).setItem(
                    item = item,
                    onItemClicked = onItemClicked,
                    onDeleteClick = onDeleteClick,
                    onValueChanged = onValueChanged
                )
            }
        }

        Log.d(PERF_GRV, "END bind position=$position : ${System.currentTimeMillis() - start} ms")
    }

    fun updateData(stepControlGRV: StepControlGRV? = null, listCheckPoint: List<ControlGRVCheckPoint>? = null) {
        val newList = when {
            stepControlGRV != null -> {
                mTemplate = GRVControlStepTemplate(stepControlGRV, context)
                mTemplate.getStepRecyclerItem()
            }
            listCheckPoint != null -> {
                listCheckPoint
            }
            else -> emptyList()
        }
        submitList(newList)
    }

    companion object {
        private const val VIEW_TYPE_EDITABLE = 0
        private const val VIEW_TYPE_CHECKBOX = 1
        private const val VIEW_TYPE_FOUR_STATE = 2
        private const val VIEW_TYPE_SINGLE_STATE = 3
    }

    class DiffCallback :
        DiffUtil.ItemCallback<ControlGRVCheckPoint>() {

        override fun areItemsTheSame(oldItem: ControlGRVCheckPoint, newItem: ControlGRVCheckPoint): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ControlGRVCheckPoint, newItem: ControlGRVCheckPoint): Boolean {
            return oldItem == newItem
        }
    }
}