package com.example.myapplication.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.component.GRVControlStepTemplate
import com.example.myapplication.model.ControlGRVCheckPoint
import com.example.myapplication.model.StepControlGRV

class StepGRVListAdapter(context: Context,
                         private val onItemClicked: (Int) -> Unit,
                         private val onDeleteClick: (Int) -> Unit,
                         private val onValueChanged: () -> Unit) : RecyclerView.Adapter<StepGRVListHolder>() {

    private var mTemplate = GRVControlStepTemplate(context = context)
    private var mItems = mutableListOf<ControlGRVCheckPoint>()

    override fun getItemCount(): Int = mItems.size

    override fun getItemViewType(position: Int): Int {
        return when (mItems[position]) {
            is ControlGRVCheckPoint.EditableCheckPoint -> VIEW_TYPE_EDITABLE
            is ControlGRVCheckPoint.CheckBoxCheckPoint -> VIEW_TYPE_CHECKBOX
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepGRVListHolder {

        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_EDITABLE -> StepGRVListHolder.EditableCheckPoint(
                inflater.inflate(R.layout.item_grv_data_card_editable, parent, false))

            VIEW_TYPE_CHECKBOX -> StepGRVListHolder.CheckBoxCheckPoint(
                inflater.inflate(R.layout.item_grv_data_card_checkable, parent, false))

            else -> throw IllegalArgumentException("Unknown viewType : $viewType")
        }
    }

    override fun onBindViewHolder(holder: StepGRVListHolder, position: Int) {
        when (val item = mItems[position]) {
            is ControlGRVCheckPoint.EditableCheckPoint -> (holder as StepGRVListHolder.EditableCheckPoint).setItem(item, onItemClicked, onDeleteClick, onValueChanged)
            is ControlGRVCheckPoint.CheckBoxCheckPoint -> (holder as StepGRVListHolder.CheckBoxCheckPoint).setItem(item, onItemClicked, onDeleteClick, onValueChanged)
        }
    }

    fun updateData(stepControlGRV: StepControlGRV? = null, context: Context, listCheckPoint: List<ControlGRVCheckPoint>? = null) {
        stepControlGRV?.let { stepControl ->
            mTemplate = GRVControlStepTemplate(stepControl, context = context)
            mItems.clear()
            mItems.addAll(mTemplate.getStepRecyclerItem())
        }

        listCheckPoint?.let {
            mItems.clear()
            mItems.addAll(it)
        }

        notifyDataSetChanged()
    }

    companion object {
        private const val VIEW_TYPE_EDITABLE = 0
        private const val VIEW_TYPE_CHECKBOX = 2
    }
}