package com.example.myapplication.utils

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRVCheckPoint
import com.example.myapplication.model.StepControlGRV

fun getStep3RecyclerItem(data: StepControlGRV.Step3ControlGRV?, mContext: Context): List<ControlGRVCheckPoint> =
    listOf(
        ControlGRVCheckPoint.FourStateCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_bottom_retention_face),
            value = data?.bottomRetentionFace,
            isChecked = data?.bottomRetentionFace != null
        ),

        ControlGRVCheckPoint.FourStateCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_bottom_retention_right),
            value = data?.bottomRetentionRight,
            isChecked = data?.bottomRetentionRight != null
        ),

        ControlGRVCheckPoint.FourStateCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_bottom_retention_left),
            value = data?.bottomRetentionLeft,
            isChecked = data?.bottomRetentionLeft != null
        ),

        ControlGRVCheckPoint.FourStateCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_bottom_retention_behind),
            value = data?.bottomRetentionBehind,
            isChecked = data?.bottomRetentionBehind != null
        ),

        ControlGRVCheckPoint.FourStateCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_upper_retention),
            value = data?.upperRetention,
            isChecked = data?.upperRetention != null
        ),

        ControlGRVCheckPoint.FourStateCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_lifting_rings),
            value = data?.liftingRings,
            isChecked = data?.liftingRings != null
        ),

        ControlGRVCheckPoint.FourStateCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_forklift_pass),
            value = data?.forkliftPass,
            isChecked = data?.forkliftPass != null
        ),

        ControlGRVCheckPoint.FourStateCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_dashboard),
            value = data?.dashboard,
            isChecked = data?.dashboard != null
        ),

        ControlGRVCheckPoint.SingleCheckCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_unauthorized_repair),
            value = data?.unauthorizedRepair ?: false
        )
    )