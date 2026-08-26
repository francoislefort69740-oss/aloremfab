package com.example.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.database.entities.ControlGRVStep5Local.Companion.GRV_CONTROL_UID
import com.example.data.database.entities.ControlGRVStep5Local.Companion.REFERENCE
import com.example.data.database.entities.ControlGRVStep5Local.Companion.TABLE_NAME


@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = ControlGRVLocal::class,
            parentColumns = [ControlGRVLocal.UID],
            childColumns = [GRV_CONTROL_UID],
            onDelete = ForeignKey.CASCADE
        )],
    indices = [Index(value = [REFERENCE], unique = true)]
)
data class ControlGRVStep5Local(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = REFERENCE) val reference: Int,
    @ColumnInfo(name = GRV_CONTROL_UID, index = true) val foreignKey: Int,
    @ColumnInfo(name = EPAISSEUR_NA) val epaisseurNA: Boolean,
    @ColumnInfo(name = EPAISSEUR_MIN_SIDE_FRONT) val epaisseurMinSideFront: Int,
    @ColumnInfo(name = EPAISSEUR_MIN_SIDE_BACK) val epaisseurMinSideBack: Int,
    @ColumnInfo(name = EPAISSEUR_MIN_SIDE_RIGHT) val epaisseurMinSideRight: Int,
    @ColumnInfo(name = EPAISSEUR_MIN_SIDE_LEFT) val epaisseurMinSideLeft: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_FRONT_RESULT_1) val epaisseurSideFrontResult1: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_FRONT_RESULT_2) val epaisseurSideFrontResult2: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_FRONT_RESULT_3) val epaisseurSideFrontResult3: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_FRONT_RESULT_4) val epaisseurSideFrontResult4: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_FRONT_RESULT_5) val epaisseurSideFrontResult5: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_BACK_RESULT_1) val epaisseurSideBackResult1: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_BACK_RESULT_2) val epaisseurSideBackResult2: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_BACK_RESULT_3) val epaisseurSideBackResult3: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_BACK_RESULT_4) val epaisseurSideBackResult4: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_BACK_RESULT_5) val epaisseurSideBackResult5: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_RIGHT_RESULT_1) val epaisseurSideRightResult1: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_RIGHT_RESULT_2) val epaisseurSideRightResult2: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_RIGHT_RESULT_3) val epaisseurSideRightResult3: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_RIGHT_RESULT_4) val epaisseurSideRightResult4: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_RIGHT_RESULT_5) val epaisseurSideRightResult5: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_LEFT_RESULT_1) val epaisseurSideLeftResult1: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_LEFT_RESULT_2) val epaisseurSideLeftResult2: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_LEFT_RESULT_3) val epaisseurSideLeftResult3: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_LEFT_RESULT_4) val epaisseurSideLeftResult4: Int,
    @ColumnInfo(name = EPAISSEUR_SIDE_LEFT_RESULT_5) val epaisseurSideLeftResult5: Int
){
    companion object {
        const val TABLE_NAME = "controlGRVStep5InternalData"
        const val REFERENCE = "GRVStep5Reference"
        const val EPAISSEUR_NA = "epaisseur_na"
        const val EPAISSEUR_MIN_SIDE_FRONT = "epaisseur_min_side_front"
        const val EPAISSEUR_MIN_SIDE_BACK = "epaisseur_min_side_back"
        const val EPAISSEUR_MIN_SIDE_RIGHT = "epaisseur_min_side_right"
        const val EPAISSEUR_MIN_SIDE_LEFT = "epaisseur_min_side_left"
        const val EPAISSEUR_SIDE_FRONT_RESULT_1 = "epaisseur_side_front_result_1"
        const val EPAISSEUR_SIDE_FRONT_RESULT_2 = "epaisseur_side_front_result_2"
        const val EPAISSEUR_SIDE_FRONT_RESULT_3 = "epaisseur_side_front_result_3"
        const val EPAISSEUR_SIDE_FRONT_RESULT_4 = "epaisseur_side_front_result_4"
        const val EPAISSEUR_SIDE_FRONT_RESULT_5 = "epaisseur_side_front_result_5"
        const val EPAISSEUR_SIDE_BACK_RESULT_1 = "epaisseur_side_back_result_1"
        const val EPAISSEUR_SIDE_BACK_RESULT_2 = "epaisseur_side_back_result_2"
        const val EPAISSEUR_SIDE_BACK_RESULT_3 = "epaisseur_side_back_result_3"
        const val EPAISSEUR_SIDE_BACK_RESULT_4 = "epaisseur_side_back_result_4"
        const val EPAISSEUR_SIDE_BACK_RESULT_5 = "epaisseur_side_back_result_5"
        const val EPAISSEUR_SIDE_RIGHT_RESULT_1 = "epaisseur_side_right_result_1"
        const val EPAISSEUR_SIDE_RIGHT_RESULT_2 = "epaisseur_side_right_result_2"
        const val EPAISSEUR_SIDE_RIGHT_RESULT_3 = "epaisseur_side_right_result_3"
        const val EPAISSEUR_SIDE_RIGHT_RESULT_4 = "epaisseur_side_right_result_4"
        const val EPAISSEUR_SIDE_RIGHT_RESULT_5 = "epaisseur_side_right_result_5"
        const val EPAISSEUR_SIDE_LEFT_RESULT_1 = "epaisseur_side_left_result_1"
        const val EPAISSEUR_SIDE_LEFT_RESULT_2 = "epaisseur_side_left_result_2"
        const val EPAISSEUR_SIDE_LEFT_RESULT_3 = "epaisseur_side_left_result_3"
        const val EPAISSEUR_SIDE_LEFT_RESULT_4 = "epaisseur_side_left_result_4"
        const val EPAISSEUR_SIDE_LEFT_RESULT_5 = "epaisseur_side_left_result_5"
        const val GRV_CONTROL_UID = "GRV_control_uid"
    }

}
