package com.example.myapplication.utils

import android.content.Context
import android.text.InputType
import com.example.myapplication.R
import com.example.myapplication.model.ControlGRVCheckPoint
import com.example.myapplication.model.StepControlGRV

fun getStep5RecyclerItem(data: StepControlGRV.Step5ControlGRV?, mContext: Context): List<ControlGRVCheckPoint> =
    listOf(
        ControlGRVCheckPoint.SingleCheckCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_na),
            value = data?.epaisseurNA ?: false,
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_min_side_front),
            value = data?.epaisseurMinSideFront
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_min_side_back),
            value = data?.epaisseurMinSideBack
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_min_side_right),
            value = data?.epaisseurMinSideRight
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_min_side_left),
            value = data?.epaisseurMinSideLeft
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_front_result_1),
            value = data?.epaisseurSideFrontResult1
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_front_result_2),
            value = data?.epaisseurSideFrontResult2
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_front_result_3),
            value = data?.epaisseurSideFrontResult3
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_front_result_4),
            value = data?.epaisseurSideFrontResult4
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_front_result_5),
            value = data?.epaisseurSideFrontResult5
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_back_result_1),
            value = data?.epaisseurSideBackResult1
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_back_result_2),
            value = data?.epaisseurSideBackResult2
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_back_result_3),
            value = data?.epaisseurSideBackResult3
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_back_result_4),
            value = data?.epaisseurSideBackResult4
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_back_result_5),
            value = data?.epaisseurSideBackResult5
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_right_result_1),
            value = data?.epaisseurSideRightResult1
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_right_result_2),
            value = data?.epaisseurSideRightResult2
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_right_result_3),
            value = data?.epaisseurSideRightResult3
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_right_result_4),
            value = data?.epaisseurSideRightResult4
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_right_result_5),
            value = data?.epaisseurSideRightResult5
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_left_result_1),
            value = data?.epaisseurSideLeftResult1
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_left_result_2),
            value = data?.epaisseurSideLeftResult2
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_left_result_3),
            value = data?.epaisseurSideLeftResult3
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_left_result_4),
            value = data?.epaisseurSideLeftResult4
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        ),
        ControlGRVCheckPoint.EditableCheckPoint(
            name = mContext.getString(R.string.control_grv_checkpoint_epaisseur_side_left_result_5),
            value = data?.epaisseurSideLeftResult5
                ?.takeIf { it != 0 }
                ?.toString()
                .orEmpty(),
            isEnable = data?.epaisseurMinSideFront == null || data.epaisseurMinSideFront == 0,
            inputType = InputType.TYPE_CLASS_NUMBER
        )
    )
