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
import com.example.myapplication.utils.CheckPointHolderEnum
import com.example.myapplication.utils.PERF_GRV
import com.example.myapplication.utils.returnHolder
import com.example.myapplication.utils.returnItemViewType

class StepGRVListAdapter(
    private val context: Context,
    private val onItemClicked: (Int) -> Unit,
    private val onDeleteClick: (Int) -> Unit,
    private val onValueChanged: () -> Unit
) : ListAdapter<ControlGRVCheckPoint, StepGRVListHolder>(DiffCallback()) {

    private var mTemplate = GRVControlStepTemplate(context = context)

    override fun getItemViewType(position: Int): Int {
        return returnItemViewType(item = getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepGRVListHolder {
        Log.d(PERF_GRV, "onCreateViewHolder type=$viewType")
        return returnHolder(parent = parent, viewType = CheckPointHolderEnum.getStep(viewType), inflater = LayoutInflater.from(parent.context))
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